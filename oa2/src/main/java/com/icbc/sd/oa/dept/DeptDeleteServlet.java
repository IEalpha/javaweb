package com.icbc.sd.oa.dept;

import com.icbc.sd.oa.utils.DbUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeptDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        String deptNo = req.getParameter("dept_no");
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
            req.getRequestDispatcher("/dept/list").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/error.html");
        }
    }
}
