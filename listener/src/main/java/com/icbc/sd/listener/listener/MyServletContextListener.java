package com.icbc.sd.listener.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Locale;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Locale locale = Locale.getDefault();
        PrintWriter out = new PrintWriter(System.out, true, Charset.defaultCharset());
        out.println("ServletContext对象创建");
        out.println(locale.getLanguage());
        System.out.println("ServletContext对象创建");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        PrintWriter out = new PrintWriter(System.out, true, Charset.defaultCharset());
        out.println("ServletContext对象销毁");
        System.out.println("ServletContext对象销毁");
    }
}
