package com.icbc.sd.listener.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.io.PrintWriter;
import java.nio.charset.Charset;

@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        PrintWriter out = new PrintWriter(System.out, true, Charset.defaultCharset());
        out.println("HttpSession对象添加属性");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        PrintWriter out = new PrintWriter(System.out, true, Charset.defaultCharset());
        out.println("HttpSession对象移除属性");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        PrintWriter out = new PrintWriter(System.out, true, Charset.defaultCharset());
        out.println("HttpSession对象替换属性");
    }
}
