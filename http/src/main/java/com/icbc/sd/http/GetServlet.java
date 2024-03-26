package com.icbc.sd.http;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class GetServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>get in post</h1>");
    }

    //    @Override
//    public void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
//        Object username = servletRequest.getAttribute("username");
//        String method = servletRequest.getMethod();
//        servletResponse.setContentType("text/html");
//        PrintWriter writer = servletResponse.getWriter();
//        writer.println("<!DOCTYPE html>");
//        writer.println("<html>");
//        writer.println("<head></head>");
//        writer.println("<body><h1>");
//        writer.println(username + "get" + method);
//        writer.println("</h1></body></html>");
//    }
}
