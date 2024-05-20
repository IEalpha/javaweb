package com.icbc.sd.jstl;

import jakarta.servlet.*;

import java.io.IOException;

public class Filter1 implements Filter {

    public Filter1() {
        System.out.println("Filter1 constructor");
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter1 init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("Filter1 doFilter before");
        chain.doFilter(request, response);
        System.out.println("Filter1 doFilter after");
    }

    @Override
    public void destroy() {
        System.out.println("Filter1 destroy");
    }
}
