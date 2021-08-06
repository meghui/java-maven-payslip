package com.example;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Meggie Xuan Hui
 * @since 5/8/21
 * For user to upload csv file to batch-create payslips
 */
@WebServlet(name = "csvFileServlet", value="/csvFileServlet")
@MultipartConfig
public class CSVFileServlet extends HttpServlet {

    private static final char DEFAULT_CSV_SEPARATOR = ',';

    public static List<List<String>> parseCsv(InputStream csvreader, char defaultCsvSeparator) {
        return parseCsv(csvreader, DEFAULT_CSV_SEPARATOR);
    }
    public CSVFileServlet(){
        super();
    }

    //doPost() gets and processes form data
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, FileNotFoundException {

        Part file = request.getPart("csvFile");


//        Files.lines(Paths(file));
        String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();
        String path = getClass().getResource(fileName).getPath();
        System.out.println(path);
        List<List<String>> csvLineList = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path)); //FileReader Looking for in <resources> folder

        String csv;

        while(bufferedReader.readLine() != null) {
            try {
                csv = bufferedReader.readLine();
                String[] csvLine = csv.split(",");
                csvLineList.add(Arrays.asList(csvLine));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        String message = null;
        for(List<String> csvLine: csvLineList){
            System.out.println(csvLine);
            message = csvLine.toString();
        }




        //print payslip or error message to index.jsp;
        request.setAttribute("message", message);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
