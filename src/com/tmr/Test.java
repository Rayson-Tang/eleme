package com.tmr;

import com.tmr.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
//        String businessName = "饺子";
//        String businessAddress = "沈阳";
//        StringBuilder sql = new StringBuilder("select * from business where 1=1");
//        sql.append(" and businessName like '%").append(businessName).append("%'");
//        sql.append(" and businessAddress like '%").append(businessAddress).append("%'");
//        System.out.println(sql);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "insert into business values(null,?,?,?,?,?,?)";
        try {
            int n = 0;
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                n = rs.getInt("businessId");
            }

            System.out.println(n);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
