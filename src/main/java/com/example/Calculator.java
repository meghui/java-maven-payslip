package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Meggie Xuan Hui
 * @since 3/8/21
 */
public class Calculator {
    public int calGrossIncome (int annualSalary) {
        return Math.round(((float)annualSalary) / 12);
    }

    public int calIncomeTax (int annualSalary) {
        int incomeTax = -1;
            if (annualSalary <= 18200) { //TODO replace number to variable in case of change
                incomeTax = 0;
            }

            if (annualSalary <= 37000 && annualSalary > 18200) {
                incomeTax = (int)Math.round(0.19 * ((float)annualSalary - 18200)/12);
            }

            if (annualSalary <= 87000 && annualSalary > 37000) {
                incomeTax = (int)Math.round((3572 + 0.325 * ((float)annualSalary - 37000))/12);
            }

            if (annualSalary <= 180000 && annualSalary > 87000) {
                incomeTax = (int)Math.round((19822 + 0.37 * ((float)annualSalary - 87000))/12);
            }

            if (annualSalary  > 180000) {
                incomeTax = (int)Math.round((54232 + 0.45 * (float)annualSalary - 180000)/12);
            }
            return incomeTax;
    }

    public int calSuper (int annualSalary, float rate) {
        return Math.round(calGrossIncome(annualSalary) * rate);
    }

    public int calNetIncome (int annualSalary) {
        return calGrossIncome(annualSalary) - calIncomeTax(annualSalary);
    }

    //calEndDate() calculates the end date of the month based on the startDate
    //calEndDate() is the most time-consuming among all the methods in com.example.Employee.java
    public String calEndDate (String startDate)  {//1 March
        //substring() remove day and space
        String month = String.valueOf(mapMonthLettersToNum(startDate.substring(startDate.indexOf(" ")+1)));
        startDate = startDate.substring(0,startDate.indexOf(" ")) + " " + month + " 2021"; //1 3 -> 1 3 2021
        LocalDate convertedSD = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("d M yyyy")); //String "1 3 2021" to LocalDate;LocalDate.parse() parses String date to LocalDate

        LocalDate lastDateOfMonth = LocalDate.of(convertedSD.getYear(), convertedSD.getMonth(), convertedSD.getMonth().length(convertedSD.isLeapYear())); //2021-03-31;LocalDate.withDayOfMonth() gets the length of the month; LocalDate.getMonth() gets the month of given date; LocalDate.isLeapYear() adds one day for Feb if leap year;
        String monthEnd = lastDateOfMonth.toString();//2021-03-31
        //DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(lastDateOfMonth);//In IntelliJ:31 Mar 2021; In Google Cloud Minikube, Mar 31, 2021

        String st = monthEnd.substring(monthEnd.indexOf("-")+1);//2021-03-31 -> 03-31
        String day = st.substring(st.indexOf("-")+1);
        month = st.substring(0, st.indexOf("-")); //03-31 -> 03
        month = mapMonthNumToLetters(month);// 03 -> March
        return day + " "+ month; // 31 March
    }


    //mapMonthLetterToNum maps the month to its number presenting
    public String mapMonthLettersToNum (String monthLetters) {
        String monthNum = "-1";
        switch (monthLetters.toLowerCase()) {
            case "january":
            case "jan":
                monthNum = "1";
                break;
            case "february":
            case "feb":
                monthNum = "2";
                break;
            case "march":
            case "mar":
                monthNum = "3";
                break;
            case "april":
            case "apr":
                monthNum = "4";
                break;
            case "may":
                monthNum = "5";
                break;
            case "june":
            case "jun":
                monthNum = "6";
                break;
            case "july":
            case "jul":
                monthNum = "7";
                break;
            case "august":
            case "aug":
                monthNum = "8";
                break;
            case "september":
            case "sep":
                monthNum = "9";
                break;
            case "october":
            case "oct":
                monthNum = "10";
                break;
            case "november":
            case "nov":
                monthNum = "11";
                break;
            case "december":
            case "dec":
                monthNum = "12";
                break;
        }
        return monthNum;
    }
    public String mapMonthNumToLetters (String monthNum) {
        String monthLetters = null;
        if (monthNum.charAt(0) == '0') {
            monthNum = monthNum.substring(1);
        }
        switch (monthNum) {
            case "1":
                monthLetters = "January";
                break;
            case "2":
                monthLetters = "February";
                break;
            case "3":
                monthLetters = "March";
                break;
            case "4":
                monthLetters = "April";
                break;
            case "5":
                monthLetters = "May";
                break;
            case "6":
                monthLetters = "June";
                break;
            case "7":
                monthLetters = "July";
                break;
            case "8":
                monthLetters = "August";
                break;
            case "9":
                monthLetters = "September";
                break;
            case "10":
                monthLetters = "October";
                break;
            case "11":
                monthLetters = "November";
                break;
            case "12":
                monthLetters = "December";
                break;
        }
        return monthLetters;
    }

}
