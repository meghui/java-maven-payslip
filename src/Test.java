import org.w3c.dom.ls.LSOutput;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Scanner;


/**
 * This class is to test the app
 * @author huixuan
 * @since 30/7/21
 */
public class Test {

    public static void main(String[] args) {
        System.out.print("Please input the csv for employee 1:");
        Scanner sc = new Scanner(System.in);
        String csv = sc.nextLine();
        Employee emp1 = new Employee();
        //emp1Input = "Monica,Tan,60050,9%,1 March";
        emp1.output(csv);
        System.out.print("Please input the csv for employee 2:");
        csv = sc.nextLine();
        Employee emp2 = new Employee();
        //emp2Input = "Brend,Tulu,120000,10%,1 March";
        emp2.output(csv);
    }
}
