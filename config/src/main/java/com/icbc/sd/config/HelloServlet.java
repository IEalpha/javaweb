package com.icbc.sd.config;

import java.io.*;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "helloServlet", value = "/hello-servlet", initParams = {
        @WebInitParam(name = "message", value = "Hello World!")
})
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        ServletConfig servletConfig = super.getServletConfig();
        System.out.println(servletConfig.getServletName());
        servletConfig.getInitParameterNames().asIterator().forEachRemaining(System.out::println);
        // Hello
        ServletContext servletContext = this.getServletContext();
        System.out.println(servletContext.getServletContextName());
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}