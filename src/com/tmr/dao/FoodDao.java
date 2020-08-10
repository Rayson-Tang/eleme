package com.tmr.dao;

import com.tmr.domain.Food;

import java.util.List;

public interface FoodDao {
    public List<Food> foodList(Integer businessId);

    public int saveFood(Food food);

    public int updateFood(Integer foodId, String column, String newMsgd);

    public int removeFood(Integer foodId);

    public Food getFoodById(Integer foodId);
}
