package com.tmr.view;

import com.tmr.domain.Food;

import java.util.List;

public interface FoodView {
    public List<Food> showFoodList(Integer businessId);

    public void saveFood(Integer businessId);

    public void updateFood(Integer foodId);

    public void removeFood(Integer businessId);
}
