package com.icbc.sd.context;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

public class AServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        ServletContext servletContext = this.getServletContext();
        servletContext.setAttribute("aaa", "ccc");
        servletResponse.setContentType("text/html");
        PrintWriter writer = servletResponse.getWriter();
        writer.print("<h1>" + servletContext.getAttribute("aaa") + "</h1>");

    }
}
