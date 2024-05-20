package com.icbc.sd.listener.model;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

import java.io.PrintWriter;
import java.nio.charset.Charset;

public class User implements HttpSessionBindingListener {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        PrintWriter out = new PrintWriter(System.out, true, Charset.defaultCharset());
        out.println("User对象被绑定到了session中");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        PrintWriter out = new PrintWriter(System.out, true, Charset.defaultCharset());
        out.println("User对象被从session中移除了");
    }
}
