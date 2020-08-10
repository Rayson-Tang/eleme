package com.Rayson.dao.Impl;

import com.Rayson.dao.FoodDao;
import com.Rayson.domain.Food;
import com.Rayson.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public List<Food> foodList(Integer businessId) {
        String sql = "select * from food where businessId = ?";
        ArrayList<Food> foodList = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, businessId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setFoodId(rs.getInt("foodId"));
                food.setFoodName(rs.getString("foodName"));
                food.setFoodExplain(rs.getString("foodExplain"));
                food.setFoodPrice(rs.getDouble("foodPrice"));
                foodList.add(food);
            }
        }
        catch (
                SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        return foodList;
    }

    @Override
    public int saveFood(Food food) {
        String sql = "insert into food values(null,?,?,?,?)";
        int foodId = 0;
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, food.getFoodName());
            pstmt.setString(2, food.getFoodExplain());
            pstmt.setDouble(3, food.getFoodPrice());
            pstmt.setDouble(4, food.getBusinessId());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            while (rs.next()) {
                foodId = rs.getInt(1);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        return foodId;
    }


    @Override
    public int updateFood(Integer foodId, String column, String newMsg) {
        int result = 0;
        StringBuilder sql = new StringBuilder("update food set ");
        sql.append(column).append(" = '").append(newMsg).append("' where foodId = ").append(foodId);
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
    public int removeFood(Integer foodId) {
        String sql = "delete from food where foodId = ?";
        int result = 0;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodId);
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
    public Food getFoodById(Integer foodId) {
        return null;
    }
}
