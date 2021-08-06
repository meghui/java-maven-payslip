package com.example;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Meggie Xuan Hui
 * @since 4/8/21
 */
public class Validator {

    public String checkMissing (String string) {
        String msg = "";
        //Format the output to adopt in jsp. I tried putting at the end instead of each message. But it is prettier if every error occupies one line.
        String tagA = "<p>";
        String tagB = "</p>";

        HashMap<Integer, String> errors = new HashMap<>();
        errors.put(0, "First Name cannot be empty.");
        errors.put(1, "Last Name cannot be empty.");
        errors.put(2, "Annual Salary cannot be empty.");
        errors.put(3, "Super Rate cannot be empty.");
        errors.put(4, "Payment Start Date cannot be empty.");

        String[] strings = string.trim().split(",");
        for (int i = 0; i<strings.length; i++){
            if ( strings[i].equals("")) {
                msg += tagA + errors.get(i) + tagB;
            }
        }
        return msg;
    }

    public String checkIllegals (CSV csv) {
        String msg = "";
        //Format the output to adopt in jsp. I tried putting at the end instead of each message. But it is prettier if every error occupies one line.
        String tagA = "<p>";
        String tagB = "</p>";
        //check if firstName is longer than 50; Setting limitation in case long one explodes the disk
        if (csv.getFirstName().length() > 50) {// control the length
            msg += tagA + "First Name: No longer than 50 letters." + tagB;
        }

        //check if firstName contains symbol characters.
        Pattern patternName = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\\\_^]");
        Matcher matcher = patternName.matcher(csv.getFirstName());
        if (matcher.find()) {
            msg += tagA + "First Name: Cannot start with and contain alphabetic character." + tagB;
        }

        //check if lastName is longer than 50; Setting limitation in case long one explodes disk
        if (csv.getLastName().length() > 50) {// control the length
            msg += tagA + "Last Name: No longer than 50 letters" + tagB;
        }

        matcher = patternName.matcher(csv.getLastName());
        if (matcher.find()) {
            msg += tagA + "Last Name: Cannot start with and contain only alphabetic character." + tagB;
        }

        //Check Annual Salary
        Pattern patterSalary = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\\\_^]");
        matcher = patterSalary.matcher(csv.getAnnualSalary());
        if (matcher.find()) {
            msg += tagA + "Annual Salary: Cannot start with and/or contain illegal character" + tagB;
        } else {
            if (Integer.parseInt(csv.getAnnualSalary()) <= 0) {
                msg += tagA + "Annual Salary: Cannot be negative integer or 0." + tagB;
            }
        }

        //Check Super Rate
        Pattern patterRate = Pattern.compile("[~#@*+{}<>\\[\\]|\"\\\\_^]");
        matcher = patterRate.matcher(csv.getSuperRate());
        if (matcher.find()) {
            msg += tagA + "Super Rate: Cannot start with and/or contain symbol except %." + tagB;
        } else if (csv.getSuperRate().contains("%")) {
            if (csv.getSuperRate().indexOf("%") != csv.getSuperRate().length() - 1) {
                msg += tagA + "Super Rate: cannot be " + csv.getSuperRate() + " which makes no sense!" + tagB;
            } else if (Integer.parseInt(csv.getSuperRate().substring(0, csv.getSuperRate().indexOf("%"))) < 0) {
                msg += tagA + "Super Rate: Cannot be negative number." + tagB;
            } else if (Integer.parseInt(csv.getSuperRate().substring(0, csv.getSuperRate().indexOf("%"))) > 50) {
                msg += tagA + "Super Rate: Cannot be over 50%." + tagB;
            }
        }else if (Float.parseFloat(csv.getSuperRate().trim()) > 0.5) {
                msg += tagA + "Super Rate: Cannot be over 50%." + tagB;

        }

            //Check Payment Start Date
            Pattern patternStartDate = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\\\_^]");
            matcher = patternStartDate.matcher(csv.getStartDateOfPayment());
            if (matcher.find()) {
                msg += tagA + "Payment Start Date: Cannot start with and/or contain symbol." + tagB;
            } else if (csv.getStartDateOfPayment().substring(csv.getStartDateOfPayment().indexOf(" ") + 1).trim().contains(" ")) {
                msg += tagA + "Payment Start Date: day and month of start date only." + tagB;

            } else if (!checkDayMonth(csv.getStartDateOfPayment()).equals("")) {
                msg += tagA + checkDayMonth(csv.getStartDateOfPayment()) + tagB;
            }

            return msg;
        }


    private String checkDayMonth (String date){
        String message = "";
        String day = "";
        String month = "";
        String parts[] = date.split(" ",2);
        day = parts[0].trim();
        month = parts[1].trim().toLowerCase();
        //day check
        if(day.contains(".")){
            message = "Payment Start Date: day cannot be float or double";
        }else {
            if ((month.equals("jan")
                    || month.equals("january")
                    || month.equals("mar")
                    || month.equals("march")
                    || month.equals("may")
                    || month.equals("jul")
                    || month.equals("july")
                    || month.equals("aug")
                    || month.equals("august")
                    || month.equals("oct")
                    || month.equals("october")
                    || month.equals("dec")
                    || month.equals("december")
            ) && Integer.parseInt(day) > 31) {
                message = "Payment Start Date: the day of this month cannot be over 31.";
            }
            if((month.equals("apr")
                    ||month.equals("april")
                    ||month.equals("jun")
                    ||month.equals("june")
                    ||month.equals("sep")
                    ||month.equals("september")
                    ||month.equals("nov")
                    ||month.equals("november") ) && Integer.parseInt(day) > 30){
                message = "Payment Start Date: the day of this month cannot be over 30.";
            }

            if((month.equals("feb")
                    ||month.equals("february")) && Integer.parseInt(day) > 28){
                message = "Payment Start Date: the day of this month cannot be over 28.";
            }

            if (Integer.parseInt(day) <= 0 ){
                message = "Payment Start Date: the day cannot be negative number";
            }
        }


        //month check
        if (!month.equals("jan")
                && !month.equals("january")
                && !month.equals("february")
                && !month.equals("feb")
                && !month.equals("march")
                && !month.equals("mar")
                && !month.equals("april")
                && !month.equals("apr")
                && !month.equals("may")
                && !month.equals("june")
                && !month.equals("jun")
                && !month.equals("july")
                && !month.equals("jul")
                && !month.equals("august")
                && !month.equals("aug")
                && !month.equals("september")
                && !month.equals("sep")
                && !month.equals("october")
                && !month.equals("oct")
                && !month.equals("november")
                && !month.equals("nov")
                && !month.equals("december")
                && !month.equals("dec")){
            message += "Payment Start Date: the spelling of month is incorrect.";
        }
        return message;
    }
}
