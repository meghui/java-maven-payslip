package com.example;

/**
 * @author Meggie Xuan Hui
 * @since 3/8/21
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
 */
public class Parser {

    public PaySlip parsePaySlip (Employee employee) {

        PaySlip payslip = new PaySlip();

        Calculator calculator = new Calculator();

        payslip.setFullName(employee.getFirstName() + " " + employee.getLastName());
        payslip.setPayPeriod(employee.getStartDateOfPayment() + " - " + calculator.calEndDate(employee.getStartDateOfPayment()));
        payslip.setGrossIncome(calculator.calGrossIncome(employee.getAnnualSalary()));
        payslip.setIncomeTax(calculator.calIncomeTax(employee.getAnnualSalary()));
        payslip.setNetIncome(calculator.calNetIncome(employee.getAnnualSalary()));
        payslip.setSuperValue(calculator.calSuper(employee.getAnnualSalary(), employee.getSuperRate()));

        return payslip;

    }


    public Employee parseEmployee (CSV csv) {
        Employee emp = new Employee();

        emp.setFirstName(csv.getFirstName().trim());
        emp.setLastName(csv.getLastName().trim());
        emp.setAnnualSalary(Integer.parseInt(csv.getAnnualSalary()));
        emp.setSuperRate(Float.parseFloat(csv.getSuperRate()));//assume 09% 0.09
        emp.setStartDateOfPayment(csv.getStartDateOfPayment());

        return emp;
    }

    public CSV parseCSVFormatted (CSV csv) {
        CSV csvFormatted = new CSV();
        //Capitalised first letter
        csvFormatted.setFirstName(csv.getFirstName().trim().substring(0,1).toUpperCase() + csv.getFirstName().trim().substring(1).toLowerCase());
        csvFormatted.setLastName(csv.getLastName().trim().substring(0,1).toUpperCase() + csv.getLastName().trim().substring(1).toLowerCase());
        csvFormatted.setAnnualSalary(csv.getAnnualSalary().trim());
        if (csv.getSuperRate().contains("%")) {
            csvFormatted.setSuperRate(String.valueOf(Float.parseFloat(csv.getSuperRate().trim().substring(0,csv.getSuperRate().trim().indexOf("%")))/100));
        }else {
            csvFormatted.setSuperRate(String.valueOf(Float.parseFloat(csv.getSuperRate())));
        }

        csvFormatted.setStartDateOfPayment(formatStartDate(csv.getStartDateOfPayment()));

        return csvFormatted;
    }


    public CSV parseCSV (String st) {
        CSV csv = new CSV();

        String[] strings = st.split(",");
        csv.setFirstName(strings[0].trim());
        csv.setLastName(strings[1].trim());
        csv.setAnnualSalary(strings[2].trim());
        csv.setSuperRate(strings[3].trim());
        csv.setStartDateOfPayment(strings[4].trim());

        return csv;
    }
    //formatStartDate() capital first letter and change to same length
    public String formatStartDate(String startDate) {
        String parts[] = startDate.split(" ",2);
        String day = parts[0].trim();
        String month = parts[1].trim().toLowerCase();
        //change to same format of month as end date
        if(month.equals("jan")){
            month = "january";
        }
        if(month.equals("feb")){
            month = "february";
        }
        if(month.equals("mar")){
            month = "march";
        }
        if(month.equals("apr")){
            month = "april";
        }
        if(month.equals("jun")){
            month = "june";
        }
        if(month.equals("jul")){
            month = "july";
        }
        if(month.equals("aug")){
            month = "august";
        }
        if(month.equals("sep")){
            month = "september";
        }
        if(month.equals("oct")){
            month = "october";
        }
        if(month.equals("nov")){
            month = "november";
        }
        if(month.equals("dec")){
            month = "december";
        }
        //capitalise the first letter
        month = month.substring(0,1).toUpperCase() + month.substring(1);
        startDate = day + " " + month;
        //setStartDate() adds function of adding "0" before the day if day is 1 ~ 9;1 March -> 01 March
        if (day.length() == 1) {
            startDate = "0" + startDate;
        }
        return startDate;
    }

}
