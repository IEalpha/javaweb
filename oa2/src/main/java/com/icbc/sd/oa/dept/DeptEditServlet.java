package com.icbc.sd.oa.dept;

import com.icbc.sd.oa.utils.DbUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String contextPath = req.getContextPath();
        PrintWriter writer = resp.getWriter();
        String deptNo = req.getParameter("dept_no");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String dname = null;
        String location = null;
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement("select * from dept where dept_no = ?");
            ps.setInt(1, Integer.parseInt(deptNo));
            rs = ps.executeQuery();
            if (rs.next()) {
                dname = rs.getString("dname");
                location = rs.getString("location");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtil.close(conn, ps, rs);
        }
        writer.print("    <!DOCTYPE html>");
        writer.print("    <html lang='en'>");
        writer.print("    <head>");
        writer.print("        <meta charset='UTF-8'>");
        writer.print("        <title>修改部门</title>");
        writer.print("    </head>");
        writer.print("    <body>");
        writer.print("    <form action='" + contextPath + "/dept/doEdit' method='post'>");
        writer.print("        <label>部门编号：");
        writer.print("            <input type='text' readonly name='dept_no' value='" + deptNo + "'/>");
        writer.print("        </label><br/>");
        writer.print("        <label>部门名称：");
        writer.print("            <input type='text' name='dname' value='" + dname + "'/>");
        writer.print("        </label><br/>");
        writer.print("        <label>");
        writer.print("                    部门位置：");
        writer.print("            <input type='text' name='location'  value='" + location + "'/>");
        writer.print("        </label><br/>");
        writer.print("        <input type='submit' value='修改'/>");
        writer.print("    <!--    返回上一个页面-->");
        writer.print("        <input type='button' value='返回' onclick='history.back()'>");
        writer.print("    </form>");
        writer.print("    </body>");
        writer.print("    </html>");
    }
}
