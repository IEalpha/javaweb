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

public class DeptDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        String deptNo = req.getParameter("dept_no");
        writer.print("<!DOCTYPE html>");
        writer.print("<html lang='en'>");
        writer.print("<head>");
        writer.print("    <meta charset='UTF-8'>");
        writer.print("    <title>查看部门</title>");
        writer.print("</head>");
        writer.print("<body>");
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement("select * from dept where dept_no = ?");
            ps.setString(1, deptNo);
            rs = ps.executeQuery();
            while (rs.next()) {
                String dname = rs.getString("dname");
                String location = rs.getString("location");
                writer.print("    <label>部门编号：");
                writer.print("        <input type='text' readonly name='" + deptNo + "' value='" + deptNo + "'/>");
                writer.print("    </label><br/>");
                writer.print("    <label>部门名称：");
                writer.print("        <input type='text' readonly name='" + dname + "' value='"+dname+"'/>");
                writer.print("    </label><br/>");
                writer.print("    <label>");
                writer.print("        部门位置：");
                writer.print("        <input type='text' readonly name='" + location + "' value='"+location+"'/>");
                writer.print("    </label><br/>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }
        writer.print("    <!--    返回上一个页面-->");
        writer.print("    <input type='button' value='返回' onclick='history.back()'>");
        writer.print("</body>");
        writer.print("</html>");
    }
}
