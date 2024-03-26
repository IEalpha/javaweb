package com.icbc.sd.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object name = req.getAttribute("name");
//        resp.setContentType("text/html;charset=utf-8");
        resp.setContentType("text/html");
        resp.getWriter().println("name : " + name);
        System.out.println("name = " + name);
        System.out.println("req.getContextPath() = " + req.getContextPath());
        System.out.println("req.getRemoteAddr() = " + req.getRemoteAddr());
        System.out.println(req.getRequestURI());
        System.out.println("req.getRequestURL() = " + req.getRequestURL());
        System.out.println("req.getServletPath() = " + req.getServletPath());
        System.out.println("req.getRemoteHost() = " + req.getRemoteHost());
        System.out.println("req.getLocalAddr() = " + req.getLocalAddr());
    }
}
