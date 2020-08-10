package com.Rayson.view;

import com.Rayson.domain.Food;

import java.util.List;

public interface FoodView {
    public List<Food> showFoodList(Integer businessId);

    public void saveFood(Integer businessId);

    public void updateFood(Integer foodId);

    public void removeFood(Integer businessId);
}
