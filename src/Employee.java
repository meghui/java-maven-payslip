import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


/**
 * @author Meggie Xuan Hui
 * @since 30/7/21
 */
public class Employee {
    //Set to private for avoiding the risk of editing by others
    private String firstName;
    private String lastName;
    private int annualSalary;
    private float superRate;
    private String startDate; //TODO find a more efficient way to store date
    private String endDate;//TODO find a more efficient way to store date

    private String fullName = null;
    private String payPeriod = null;
    private int incomeTax = -1; // Differentiate from first tier (<18200), in case getAnnualSalary is negative integer
    private int grossIncome = -1;
    private int netIncome = -1;
    private int superValue = -1;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(int annualSalary) {
        //Filter out the invalid input with warning
        if (annualSalary > 0) {
            this.annualSalary = annualSalary;
        } else {
            System.out.println("Input must be positive integer");
        }
    }

    /**
     *getSuperRate() get
     */
    public float getSuperRate() {
        return this.superRate;
    }

    public void setSuperRate(float superRate) {
        if (superRate < 0 || superRate > 0.5) {
            superRate = -1;
        }
        this.superRate = superRate;
    }

    public float convertStringToFloat (String st) {
        return (float)(Integer.parseInt(st.substring(0,st.indexOf("%"))))/100;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        /**
         * setStartDate() adds function of adding "0" before the day if day is 1 ~ 9;
         */
        String day = startDate.substring(0, startDate.indexOf(" "));
        String zero = "0";
        if (day.length() == 1) {
            startDate = zero + startDate;
        }
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String startDate) {
        this.endDate = calEndDate(startDate);//TODO check here if not works. --It works;
    }


    public String getPayPeriod() {
        return this.payPeriod;//TODO check here if not works. --It works;);
    }

    public void setPayPeriod(String startDate, String lastName) {
        this.payPeriod = startDate + " - " + lastName;
    }

    public int getIncomeTax() {
        return this.incomeTax;
    }

    public void setIncomeTax(int annualSalary) {
        this.incomeTax = calIncomeTax(annualSalary);
    }

    public int getGrossIncome() {
        return this.grossIncome;
    }

    public void setGrossIncome(int annualSalary) {
        this.grossIncome = Math.round(annualSalary / 12);
    }

    public int getNetIncome() {
        return this.netIncome;
    }

    public void setNetIncome(int grossIncome, int incomeTax) {
        this.netIncome = grossIncome - incomeTax;
    }

    public int getSuperValue() {
        return this.superValue;
    }

    public void setSuperValue(float superRate) {
        this.superValue = calSuper(superRate);
    }


    public String getFullName() {
        return this.fullName;
    }
    //Combine firstName and lastName to full name
    public void setFullName(String firstName, String lastName) {
        this.fullName = firstName + " " + lastName;
    }

    //Calculate income tax
    public int calIncomeTax(int annualSalary) {
        if (annualSalary <= 18200) { //TODO replace number to variable in case of change
            incomeTax = 0;
        }

        if (annualSalary <= 37000 && annualSalary > 18200) {
            incomeTax = (int)Math.round(0.19 * (getAnnualSalary() - 18200)/12);
        }

        if (annualSalary <= 87000 && annualSalary > 37000) {
            incomeTax = (int)Math.round((3572 + 0.325 * (getAnnualSalary() - 37000))/12);
        }

        if (annualSalary <= 180000 && annualSalary > 87000) {
            incomeTax = (int)Math.round((19822 + 0.37 * (getAnnualSalary() - 87000))/12);
        }

        if (annualSalary  > 180000) {
            incomeTax = (int)Math.round((54232 + 0.45 * (getAnnualSalary() - 180000))/12);
        }
        return incomeTax;
    }


    //Calculate super
    public int calSuper (float rate) {
        return Math.round(getGrossIncome() * rate);
    }

    //toString() formats the output
    public String toString() {
        return getFullName() + ","
                + getPayPeriod() + ","
                + getGrossIncome() + ","
                + getIncomeTax() + ","
                + getNetIncome() + ","
                + getSuperValue() ;
    }

    //formatStartDate() formats the StartDate to the one with lettered month
    public String formatStartDateMonthToLetterRemoveYear(String startDate) {
        startDate = formatStartDateMonthToNum(startDate);
        LocalDate convertedStartDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("d M yyyy")); //String "1 3 2021" to LocalDate;LocalDate.parse() parses String date to LocalDate object;
        String formattedStartDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(convertedStartDate);
        formattedStartDate = formattedStartDate.substring(0, formattedStartDate.indexOf(" ",formattedStartDate.indexOf(" ")+1)); //.substring() removes year which is from the 2nd space;
        return formattedStartDate;
    }

    public String formatStartDateMonthToNum(String startDate) {
        //
        String month = String.valueOf(mapMonthNum(startDate.substring(startDate.indexOf(" ")+1)));
        //The input start date has no year
        startDate = startDate.substring(0,startDate.indexOf(" ")) + " " + month + " 2021"; //1 March -> 1 3 2021
        return startDate;
    }

    //mapMonthNum() maps the month to its number presenting
    public int mapMonthNum (String month) {
        int monthNum = -1;
        switch (month) {
            case "January":
                monthNum = 1;
                break;
            case "February":
                monthNum = 2;
                break;
            case "March":
                monthNum = 3;
                break;
            case "April":
                monthNum = 4;
                break;
            case "May":
                monthNum = 5;
                break;
            case "June":
                monthNum = 6;
                break;
            case "July":
                monthNum = 7;
                break;
            case "August":
                monthNum = 8;
                break;
            case "September":
                monthNum = 9;
                break;
            case "October":
                monthNum = 10;
                break;
            case "November":
                monthNum = 11;
                break;
            case "December":
                monthNum = 12;
                break;
        }
        return monthNum;
    }

    //calEndDate() calculates the end date of the month based on the startDate
    //calEndDate() is the most time-consuming among all the methods in Employee.java
    public String calEndDate (String startDate)  {
        startDate = formatStartDateMonthToNum(startDate);
        LocalDate convertedStartDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("d M yyyy")); //String "1 3 2021" to LocalDate;LocalDate.parse() parses String date to LocalDate
        LocalDate lastDateOfMonth = LocalDate.of(convertedStartDate.getYear(), convertedStartDate.getMonth(), convertedStartDate.getMonth().length(convertedStartDate.isLeapYear())); //31 3 2021;LocalDate.withDayOfMonth() gets the length of the month; LocalDate.getMonth() gets the month of given date; LocalDate.isLeapYear() adds one day for Feb if leap year;
        String formattedLastDateOfMonth = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(lastDateOfMonth);//31 March 2021
        String lastDateOfMonthNoYear = formattedLastDateOfMonth.substring(0, formattedLastDateOfMonth.indexOf(" ", formattedLastDateOfMonth.indexOf(" ")+1));
        return this.endDate = lastDateOfMonthNoYear;
    }

    //convert csv line to employee's attributes
    //3rd time I encapsulate all the logic from showing in this method. I think it increases the readability.
    public void csvConverter (String csvLine) {
        String[] attriList = csvLine.trim().split(",");//trim() to remove extra space from console scanner
        setFirstName(attriList[0]);
        setLastName(attriList[1]);
        //TODO check whether Input on page 2 in file Prereq Coding Test.docx has last date or not
        setAnnualSalary(Integer.parseInt(attriList[2]));
        float fl = convertStringToFloat(attriList[3]);
        setSuperRate(fl); //indexOf() gets the index number of %; substring() removes part starting from %; Integer.parseInt() parses the String to primitive; (float) converts the result to float after /100;
        setStartDate(attriList[4]);

        //Below attributes need calculation/formatting to set
        setFullName(getFirstName(),getLastName());
        setEndDate(formatStartDateMonthToLetterRemoveYear(getStartDate()));
        setGrossIncome(getAnnualSalary());
        setIncomeTax(getAnnualSalary());
        setSuperValue(getSuperRate());

        //Below attribute need a 3rd calculation--Can't tell but it is different from above
        setNetIncome(getGrossIncome(),getIncomeTax());
        setPayPeriod(getStartDate(),getEndDate());


    }

    //output() outputs the payslip based on the given employee's information; it shows encapsulation of the program as well as the design
    public void output (String csvLine) {
        csvConverter(csvLine);
        System.out.println(toString());
    }
}
