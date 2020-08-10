package com.Rayson.dao;

import com.Rayson.domain.Food;

import java.util.List;

public interface FoodDao {
    public List<Food> foodList(Integer businessId);

    public int saveFood(Food food);

    public int updateFood(Integer foodId, String column, String newMsgd);

    public int removeFood(Integer foodId);

    public Food getFoodById(Integer foodId);
}
