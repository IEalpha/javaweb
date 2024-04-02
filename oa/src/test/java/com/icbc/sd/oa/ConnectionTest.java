package com.icbc.sd.oa;

import com.icbc.sd.oa.utils.DbUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;

public class ConnectionTest {
    @Test
    void testConnection() throws Exception {
        Connection connection = DbUtil.getConnection();
        ResultSet resultSet = connection.prepareStatement("select * from dept").executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("dept_no") + " " + resultSet.getString("dname"));
        }
    }
}
