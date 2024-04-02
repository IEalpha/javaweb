package com.icbc.sd.oa.dept;

import com.icbc.sd.oa.utils.DbUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeptDoEditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
}
