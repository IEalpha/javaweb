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

public class DeptListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String contextPath = req.getContextPath();
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print("<!DOCTYPE html>");
        writer.print("<html lang='en'>");
        writer.print("<head>");
        writer.print("    <meta charset='UTF-8'>");
        writer.print("    <title>部门信息</title>");
        writer.print("</head>");
        writer.print("<body>");
        writer.print("<h1>部门信息</h1>");
        writer.print("<hr/>");
        writer.print("<table border='1' width='50%' align='center'>");
        writer.print("    <tr>");
        writer.print("        <th>序号</th>");
        writer.print("        <th>部门编号</th>");
        writer.print("        <th>部门名称</th>");
        writer.print("        <th>操作</th>");
        writer.print("    </tr>");
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement("select * from dept");
            rs = ps.executeQuery();

            int i = 1;
            while (rs.next()) {
                String deptNo = rs.getString("dept_no");
                String dname = rs.getString("dname");
                writer.print("    <tr>");
                writer.print("        <td>" + i + "</td>");
                writer.print("        <td>" + deptNo + "</td>");
                writer.print("        <td>" + dname + "</td>");
                writer.print("        <td>");
                writer.print("            <a href='" + contextPath + "/dept/detail?dept_no=" + deptNo + "'>查看</a>");
                writer.print("            <a href='"+contextPath+"/dept/edit?dept_no=" + deptNo + "'>编辑</a>");
                writer.print("            <a href='#' onclick='deleteDept(" + deptNo + ")'>删除</a>");
                writer.print("        </td>");
                writer.print("    </tr>");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }
        writer.print("</table>");
        writer.print("<button><a href='" + contextPath + "/dept/add'>添加部门</a></button>");
        writer.print("</body>");
        writer.print("<script type='text/javascript'>");
        writer.print("        function deleteDept(dept) {");
        writer.print("    if (confirm('确定要删除'+dept+'部门吗？')) {");
        writer.print("        window.location.href = '" + contextPath + "/dept/delete?dept_no='+dept;");
        writer.print("    }");
        writer.print("}");
        writer.print("</script>");
        writer.print("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
