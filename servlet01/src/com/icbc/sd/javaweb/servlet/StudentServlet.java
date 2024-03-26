package com.icbc.sd.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentServlet extends GenericServlet {



    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String sql = "select * from students";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        servletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter writer = servletResponse.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/world?useSSL=false&serverTimezone=UTC&publicKeyRetrieval=true&allowPublicKeyRetrieval=true";
            String username = "root";
            String password = "liuhanxu";
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                writer.print("id: " + rs.getInt("id") + " name: " + rs.getString("student_name") + "<br>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");

    }
}
