# payslip4seisma

README.txt
Java version: 11
IDE: IntelliJ IDEA
Tomcat server:9.0.46


Instruction to use the app(2 ways):

Option 1: (Regardless of web view)
1. Download the zip file named "payslip4seisma"
2. Open the project from "File" -> "Open Project" 
3. "Add Configuration" 
4. Choose "Application" and add to Configuration
5. Right-Click on "Run Test.main()"  

Option 2: 
1. Download and configurate Java JDK 11.
2. Download and configurate tomcat 9.0.46
3. Download and install maven 3.8.1
4. Run tomcat after adding dependencies in step 5;
5. Add dependencies to POM.xml(ignore maven plugin if you don’t need .war):
<build>
 …
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <version>3.3.0</version>
        </plugin>
    </plugins>
</build>
<dependencies>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>
</dependencies>

Assumptions:
1. Input is in CSV format.
2. The sequence is first name, last name, annual salary, super rate (%), payment start date.
3. The year is 2021.

Design: 
1. index.jsp
2. CSVservlet
3. CSV
    1. validate 
        1. yes -> parse to Employee
            1. no empty 
            2. not nonsense
            3. convert to right format (to int or float or date)
        2. no -> index.jsp
4. Employee
    1. calculate -> parse to PaySlip
5. PaySlip
    1. print



