package com.example;

import java.util.Scanner;

/**
 * @author Meggie Xuan Hui
 * @since 3/8/21
 */
public class Test {

    public static void main(String[] args) {

        String tagA = "Java Application:";
        String tagB = "<End>";
        String message;

        //Test employee 1
        System.out.print("Please input the csv for employee 1:");
        Scanner sc = new Scanner(System.in);
        String csvLine = sc.nextLine();
        
        //emp1Input = "Monica,Tan,60050,9%,1 March";
        if (csvLine.trim().isEmpty()) {
            message = tagA + "No input." + tagB;
            break;
        }
        
        if (csvLine.trim().split(",").length < 5) {
            message = "Invalid csv record. Valid csv record has 5 items separated by comma";
            break;
        }
        
        if (csvLine.trim().split(",").length > 5) {
            message = "Fields are over 5. Please make sure you input 5 fields only";
            break;
        } 
        
        Validator v = new Validator();
        message = v.checkMissing(csvLine.trim());
        if (message.equals("")) {
            Parser parser = new Parser();
            CSV csv = parser.parseCSV(csvLine);
            message = v.checkIllegals(csv);
            if (message.equals("")) {
                CSV csvFormatted = parser.parseCSVFormatted(csv);
                Employee employee = parser.parseEmployee(csvFormatted);
                PaySlip payslip = parser.parsePaySlip(employee);
                Printer printer = new Printer();
                message = printer.print(payslip);
            }
        }
    }
}
