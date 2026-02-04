package com.tracy;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        boolean isStrongPassword = password != null && password.length() >= 8;

        PrintWriter out = response.getWriter();
        if (isStrongPassword) {
            out.println("Welcome " + (username == null ? "" : username));
        } else {
            out.println("Hello " + (username == null ? "" : username) + ", your password is weak ty in a stronger one.");
        }
    }
}