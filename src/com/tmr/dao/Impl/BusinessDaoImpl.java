package com.tmr.dao.Impl;

import com.tmr.dao.BusinessDao;
import com.tmr.domain.Admin;
import com.tmr.domain.Business;
import com.tmr.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoImpl implements BusinessDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public List<Business> search(String businessName, String businessAddress) {
        ArrayList<Business> listAll = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select * from business where 1=1");
        if (businessName != null && !businessName.equals("")) {
            sql.append(" and businessName like '%").append(businessName).append("%'");
        }
        if (businessAddress != null && !businessAddress.equals("")) {
            sql.append(" and businessAddress like '%").append(businessAddress).append("%'");
        }
        try {

            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Business business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getDouble("starPrice"));
                business.setDeliverPrice(rs.getDouble("deliveryPrice"));
                listAll.add(business);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        return listAll;
    }

    @Override
    public Integer save(Business business) {
        String sql = "insert into business values(null,?,?,?,?,?,?)";
        int businessId = 0;
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, 123);
            pstmt.setString(2, business.getBusinessName());
            pstmt.setString(3, business.getBusinessAddress());
            pstmt.setString(4, business.getBusinessExplain());
            pstmt.setDouble(5, business.getStarPrice());
            pstmt.setDouble(6, business.getDeliverPrice());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            while (rs.next()) {
                businessId = rs.getInt(1);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        return businessId;
    }

    @Override
    public int remove(Integer businessId) {
        String sql1 = "select * from business where businessId = ?";
        String sql2 = "delete from business where businessId = ?";
        int Id = 0;
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setInt(1, businessId);
            rs = pstmt.executeQuery();
            while (rs.next()){
            Id = rs.getInt("businessId");}
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql2);
            pstmt.setInt(1, businessId);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        return Id;
    }
}
