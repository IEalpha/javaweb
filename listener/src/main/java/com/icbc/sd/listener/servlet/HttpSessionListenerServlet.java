package com.icbc.sd.listener.servlet;

import com.icbc.sd.listener.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/servlet")
public class HttpSessionListenerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = new User("张三", 20);
        User user2 = new User("李四", 30);
        session.setAttribute("user", user);
//        session.setAttribute("user", user);
//        session.setAttribute("user", user2);
//        session.removeAttribute("user");
    }
}
