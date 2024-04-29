package com.icbc.sd.oa3.auth;

import com.icbc.sd.oa3.utils.DbUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String autoLogin = request.getParameter("autoLogin");
        Cookie usernameCookie = new Cookie("username", username);
        usernameCookie.setMaxAge(60*60*24*10);
        Cookie passwordCookie = new Cookie("password", password);
        passwordCookie.setMaxAge(60*60*24*10);
        Cookie autoLoginCookie = new Cookie("autoLogin", autoLogin);
        autoLoginCookie.setMaxAge(60*60*24*10);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement("select * from user where username = ? and password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                request.getSession().setAttribute("username", username);
                if ("on".equals(autoLogin)) {
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(autoLoginCookie);
                } else {
                    usernameCookie.setMaxAge(0);
                    passwordCookie.setMaxAge(0);
                    autoLoginCookie.setMaxAge(0);
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(autoLoginCookie);
                }
                response.sendRedirect(contextPath + "/index.jsp");
            } else {
                usernameCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);
                autoLoginCookie.setMaxAge(0);
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
                response.addCookie(autoLoginCookie);
                response.sendRedirect(contextPath + "/login.jsp?error=1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
