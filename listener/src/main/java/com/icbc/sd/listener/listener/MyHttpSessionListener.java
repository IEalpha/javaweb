package com.icbc.sd.listener.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.io.PrintWriter;
import java.nio.charset.Charset;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        PrintWriter out = new PrintWriter(System.out, true, Charset.defaultCharset());
        out.println("HttpSession对象创建");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        PrintWriter out = new PrintWriter(System.out, true, Charset.defaultCharset());
        out.println("HttpSession对象销毁");
    }
}
