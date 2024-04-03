package com.icbc.sd.oa.dept;

import com.icbc.sd.oa.utils.DbUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet({"/dept/list", "/dept/add", "/dept/doAdd", "/dept/detail", "/dept/edit", "/dept/doEdit", "/dept/delete"})
public class DeptServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        switch (servletPath) {
            case "/dept/add":
                add(request, response);
                break;
            case "/dept/delete":
                delete(request, response);
                break;
            case "/dept/edit":
                edit(request, response);
                break;
            case "/dept/list":
                list(request, response);
                break;
            case "/dept/doAdd":
                doAdd(request, response);
                break;
            case "/dept/doEdit":
                doEdit(request, response);
                break;
            case "/dept/detail":
                detail(request, response);
                break;
            default:
                response.sendError(404);
        }
    }

    private void detail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

    private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String deptNo = req.getParameter("dept_no");
        String dname = req.getParameter("dname");
        String location = req.getParameter("location");
        int count = 0;
        try {
            conn = DbUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("update dept set dname = ?, location = ? where dept_no = ?");
            ps.setString(1, dname);
            ps.setString(2, location);
            ps.setString(3, deptNo);
            count = ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, null);
            if (count == 1) {
                resp.sendRedirect(req.getContextPath() + "/dept/list");
            } else {
                resp.sendRedirect(req.getContextPath() + "/error.html");
            }
        }
    }

    private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Connection conn = null;
        PreparedStatement ps = null;
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String deptNo = req.getParameter("dept_no");
        String dname = req.getParameter("dname");
        String location = req.getParameter("location");
        int count = 0;
        try {
            conn = DbUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("insert into dept(dept_no, dname, location) values(?, ?, ?)");
            ps.setString(1, deptNo);
            ps.setString(2, dname);
            ps.setString(3, location);

            count = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, null);
            if (count == 1) {
                req.getRequestDispatcher("/dept/list").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/error.html");
            }
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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


    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        String deptNo = request.getParameter("dept_no");
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement("delete from dept where dept_no = ?");
            ps.setString(1, deptNo);
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }
        if (count == 1) {
            request.getRequestDispatcher("/dept/list").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String contextPath = request.getContextPath();
        PrintWriter writer = response.getWriter();
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
