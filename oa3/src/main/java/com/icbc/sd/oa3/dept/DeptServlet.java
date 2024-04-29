package com.icbc.sd.oa3.dept;

import com.icbc.sd.oa3.bean.Dept;
import com.icbc.sd.oa3.utils.DbUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet({"/dept/list", "/dept/add", "/dept/doAdd", "/dept/detail", "/dept/edit", "/dept/doEdit", "/dept/delete"})
public class DeptServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }
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
        String deptNo = req.getParameter("dept_no");
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement("select * from dept where dept_no = ?");
            ps.setString(1, deptNo);
            rs = ps.executeQuery();
            Dept dept = new Dept();
            while (rs.next()) {
                String dname = rs.getString("dname");
                String location = rs.getString("location");
                dept.setDeptNo(deptNo);
                dept.setDname(dname);
                dept.setLocation(location);
            }
            req.setAttribute("dept", dept);
            req.getRequestDispatcher("/detail.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }
    }

    private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection conn = null;
        PreparedStatement ps = null;
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
                resp.sendRedirect(req.getContextPath() + "/error.jsp");
            }
        }
    }

    private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Connection conn = null;
        PreparedStatement ps = null;
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
                resp.sendRedirect(req.getContextPath() + "/dept/list");
            } else {
                resp.sendRedirect(req.getContextPath() + "/error.jsp");
            }
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String contextPath = req.getContextPath();
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement("select * from dept");
            rs = ps.executeQuery();
            List<Dept> deptList = new ArrayList<>();

            while (rs.next()) {
                String deptNo = rs.getString("dept_no");
                String dname = rs.getString("dname");
                String location = rs.getString("location");
                deptList.add(new Dept(deptNo, dname, location));
            }
            req.setAttribute("deptList", deptList);
            req.getRequestDispatcher("/list.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }
    }


    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            req.setAttribute("dept", new Dept(deptNo, dname, location));
            req.getRequestDispatcher("/edit.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtil.close(conn, ps, rs);
        }
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
            response.sendRedirect(request.getContextPath() + "/error.jsp");
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
