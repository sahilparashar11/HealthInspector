package com.cts.loginapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import com.cts.loginapp.bean.LoginBean;
import com.cts.loginapp.service.LoginService;
import com.cts.loginapp.service.LoginServiceImpl;

/**
* Servlet implementation class LoginServlet
*/
public class LoginServlet extends HttpServlet {
                private static final long serialVersionUID = 1L;
              
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

                
                /**
                * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
                */
                protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                                LoginService loginService = new LoginServiceImpl();
                                LoginBean bean = new LoginBean();
                                bean.setUserName(request.getParameter("userName"));
                                bean.setPassword(request.getParameter("password"));
                                System.out.println(bean);
                                RequestDispatcher dispatcher = null;
                                PrintWriter out1 = response.getWriter();
                                if(loginService.validateUser(bean)) {
                                dispatcher = request.getRequestDispatcher("search.jsp");
                                dispatcher.forward(request, response);
                                }
                                else {
                                
                                	/* out1.println("Incorrect Id or Password");
                                     RequestDispatcher dispatcher1 = request.getRequestDispatcher("/loginPage.jsp");
                                     dispatcher1.forward(request, response);
                                	request.setAttribute("message", "Invalid"); // Will be available as ${message}
                                	request.getRequestDispatcher("loginPage.jsp").forward(request,response);*/
                                 
                                	  out1.println("<script type=\"text/javascript\">");
                                	   out1.println("alert('User or password incorrect');");
                                	   out1.println("location='loginPage.jsp';");
                                	   out1.println("</script>");
                              	  
                              	  
                                	
                                          
                                }
                                
                }

}
