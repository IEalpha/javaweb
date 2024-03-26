package com.icbc.sd.http;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class PostServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        Object username = servletRequest.getAttribute("username");
        servletResponse.setContentType("text/html");
        PrintWriter writer = servletResponse.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head></head>");
        writer.println("<body><h1>");
        writer.println(username + "\npost");
        writer.println("</h1></body></html>");
    }
}
