<%--
  Created by IntelliJ IDEA.
  User: meggie
  Date: 2/8/21
  Time: 2:15 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Pay Slip Printer</title>
    <style>
      * {
        box-sizing: border-box;
      }

      body {
        font-family: Arial, Helvetica, sans-serif;
      }

      header {
        background-color: #666;
        padding: 30px;
        text-align: center;
        font-size: 35px;
        color: white;
      }

      section {
        display: -webkit-flex;
        display: flex;
      }

      nav {
        -webkit-flex: 2;
        -ms-flex: 2;
        flex: 2;
        background: #ccc;
        padding: 30px;
      }

      nav ul {
        list-style-type: none;
        padding: 0;
      }

      article {
        -webkit-flex: 3;
        -ms-flex: 3;
        flex: 3;
        background-color: #f1f1f1;
        padding: 20px;
      }

      input[type=text], select {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
      }

      .button {
        border: none;
        color: white;
        padding: 16px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        transition-duration: 0.4s;
        cursor: pointer;
      }

      .button1 {
        background-color: white;
        color: black;
        border: 2px solid #4CAF50;
      }

      .button1:hover {
        background-color: #4CAF50;
        color: white;
      }

      .button2 {
        background-color: white;
        color: black;
        border: 2px solid #008CBA;
      }

      .button2:hover {
        background-color: #008CBA;
        color: white;
      }

      footer {
        background-color: #777;
        padding: 10px;
        text-align: center;
        color: white;
      }

      @media (max-width: 600px) {
        section {
          -webkit-flex-direction: column;
          flex-direction: column;
        }
      }
    </style>

  </head>
  <body>
  <header>
    <h2><%= "Welcome to Pay Slip Printer! " %></h2>
  </header>
  <section>
    <nav>
      <nl>
        <li><small>Input["First Name,Last Name,Annual Salary,Super Rate,Payment Start Date"]</small></li>
        <li><small>Payment start date: dd MMM or dd MMMMM</small></li>
      </nl>
    </nav>
    <article>
      <form name="csv" method="post" action="csvServlet">
        <h1>CSV record:</h1>
        <input type="text" name="csvLine"><br/><br/>
        <input type="submit" value="Print Pay Slip"  class="button button1">
        <a href="index.jsp"><input type="reset" value="Reset" class="button button2"></a>
      </form>
      <h3>Result: </h3>
      ${message}
    </article>
  </section>
  </body>
</html>

