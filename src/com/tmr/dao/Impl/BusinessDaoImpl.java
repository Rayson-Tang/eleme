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
        String sql = "delete from business where businessId = ?";
        int result = 0;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, businessId);
            result = pstmt.executeUpdate();
            conn.commit();
        }
        catch (SQLException e) {
            result = 0;
            try {
                conn.rollback();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        return result;
    }

    @Override
    public Business getBusinessByNameByPassword(Integer BusinessId, String password) {
        Business business = null;
        String sql = "select * from business where businessId = ? and password = ?";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, BusinessId);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        return business;
    }

    @Override
    public List<Business> lookMsg(Integer businessId, String password) {
        ArrayList<Business> list = new ArrayList<>();
        String sql = "select * from business where businessId = ? and password = ?";

        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, businessId);
            pstmt.setString(2, password);
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
                list.add(business);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        return list;
    }

    @Override
    public Integer updateMsg(Integer Id, String column, String newMsg) {
        int result = 0;
        StringBuilder sql = new StringBuilder("update business set ");
        sql.append(column).append(" = '").append(newMsg).append("' where businessId = ").append(Id);
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            result = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(pstmt, conn);
        }
        return result;
    }

    @Override
    public Integer updatePassword(Integer Id, String newPassword) {
        int result = 0;
        String sql = "update business set password = " + newPassword + " where businessId = " + Id;
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            result = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(pstmt, conn);
        }
        return result;
    }

    @Override
    public String confirmPassword(Integer Id, String oldPassword) {
        String sql = "select password from business where businessId = " + Id;
        String old = null;
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                old = rs.getString("password");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        return old;
    }

}

