package com.test.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;

/**
* Servlet implementation class RegisterController
*/
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             // TODO Auto-generated method stub
             response.setContentType("text/html");
           PrintWriter out = response.getWriter();
           String UserType = request.getParameter("Radio");
             String FirstName = request.getParameter("fname");
             String LastName = request.getParameter("lname");
             String YourEmail = request.getParameter("email");
             String YourAge = request.getParameter("age");
             String Gender = request.getParameter("radio1");
             String YourNumber = request.getParameter("phnumber");
             String PWord = request.getParameter("password");
             String ConfirmPassword = request.getParameter("confirm");

             // validate given input
             if (FirstName.isEmpty() || LastName.isEmpty() || YourEmail.isEmpty() || PWord.isEmpty() || ConfirmPassword.isEmpty()) {
              RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
              out.println("<font color=red>Please fill all the fields</font>");
              rd.include(request, response);
             } else {
              // inserting data into mysql database 
              // create a test database and student table before running this to create table
              //create table student(name varchar(100), userName varchar(100), pass varchar(100), addr varchar(100), age int, qual varchar(100), percent varchar(100), year varchar(100));
              try {
               Class.forName("com.mysql.jdbc.Driver");
               // loads mysql driver

               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
               System.out.println(con);

               String query = "insert into Register(UserType,FirstName,LastName, userName,Yourage,Gender,YourNumber, Password , ConfirmPassword) values(?,?,?,?,?,?,?,?,?)";
              
               PreparedStatement ps = con.prepareStatement(query); // generates sql query
             
               ps.setString(1, UserType);
               ps.setString(2, FirstName);
               ps.setString(3, LastName);
               ps.setString(4, YourEmail);
               ps.setInt(5, Integer.parseInt(YourAge));
               ps.setString(6, Gender);
              
               ps.setString(7, YourNumber);
               ps.setString(8, PWord);
               ps.setString(9, ConfirmPassword);
               
               
            
               ps.executeUpdate();
              
               System.out.println("successfuly inserted");
               ps.close();
               con.close();
              } catch (ClassNotFoundException | SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
              }
              RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
              rd.forward(request, response);
             }
           }
           }
