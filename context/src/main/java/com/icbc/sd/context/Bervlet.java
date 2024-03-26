package com.icbc.sd.context;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

public class Bervlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        ServletContext servletContext = this.getServletContext();
        servletResponse.setContentType("text/html");
        PrintWriter writer = servletResponse.getWriter();
        Object aaa = servletContext.getAttribute("aaa");
        if (aaa != null) {
            writer.print("<h1>" + aaa + "</h1>");
        } else {
            writer.print("<h1>内容为空！</h1>");
        }

    }
}
