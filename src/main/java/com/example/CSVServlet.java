package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * @author Meggie Xuan Hui
 * @since 2/8/21
 *
 * The order of inputs is
 * first name,
 * last name,
 * annual salary,
 * super rate (%),
 * payment start date.
 *
 * The order of outputs on payslip is
 * Full Name
 * Period
 * Gross Income
 * Income Tax
 * Net Income
 * Super
 *
 * Double check <p></p> label added in String message if file type is changed from .jsp.
 */
@WebServlet(name = "csvServlet", value = "/csvServlet")
public class CSVServlet extends HttpServlet {

    
    public void init() throws ServletException{
    }

    //doPost() gets and processes form data
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String message = null;
        //request.getParameter() gets the input from index.jsp
        String input = request.getParameter("csvLine");

        //Format the output to adopt in jsp. I tried putting at the end instead of each message. But it is prettier if every error occupies one line.
        String tagA = "<p>";
        String tagB = "</p>";

        //process the input data
        //Check if input from index.jsp is empty, then exit
        if (input.trim().isEmpty()) {
            message = tagA + "No input." + tagB;
        }else if (input.trim().split(",").length < 5) {
            message = "Invalid csv record. Valid csv record has 5 items separated by comma";
        } else if (input.trim().split(",").length > 5){
            message = "Fields are over 5. Please make sure you input 5 fields only";
        } else {
            Validator v = new Validator();
            message = v.checkMissing(input.trim());
            if (message.equals("")) {
                Parser parser = new Parser();
                CSV csv = parser.parseCSV(input);
                message = v.checkIllegals(csv);
                if (message.equals("")) {
                    CSV csvFormatted = parser.parseCSVFormatted(csv);
                    Employee employee = parser.parseEmployee(csvFormatted);
                    PaySlip payslip = parser.parsePaySlip(employee);
                    Printer printer = new Printer();
                    message = printer.print(payslip) + " <p><small>(name, pay period, gross income, income tax, net income, super) </small></p>";
                }
            }
        }
        //print payslip or error message to index.jsp;
        request.setAttribute("message", message);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
