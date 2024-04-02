package com.icbc.sd.oa;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = {"/hello-servlet", "/helloWorld"},
initParams = {@WebInitParam(name = "message", value = "Hello-World!")})
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
        message = getInitParameter("message");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}