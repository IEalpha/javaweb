package com.icbc.sd.oa.dept;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class DeptAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String contextPath = req.getContextPath();
        PrintWriter writer = resp.getWriter();
        writer.print("    <!DOCTYPE html>");
        writer.print("    <html lang='en'>");
        writer.print("    <head>");
        writer.print("        <meta charset='UTF-8'>");
        writer.print("        <title>添加部门</title>");
        writer.print("    </head>");
        writer.print("    <body>");
        writer.print("    <form action='"+contextPath+"/dept/doAdd' method='post'>");
        writer.print("        <label>部门编号：");
        writer.print("            <input type='text' name='dept_no'/>");
        writer.print("        </label><br/>");
        writer.print("        <label>部门名称：");
        writer.print("            <input type='text' name='dname'/>");
        writer.print("        </label><br/>");
        writer.print("        <label>");
        writer.print("                    部门位置：");
        writer.print("            <input type='text' name='location'/>");
        writer.print("        </label><br/>");
        writer.print("        <input type='submit' value='添加'/>");
        writer.print("        <!--    返回上一个页面-->");
        writer.print("        <input type='button' value='返回' onclick='history.back()'>");
        writer.print("    </form>");
        writer.print("    </body>");
        writer.print("    </html>");
    }
}
