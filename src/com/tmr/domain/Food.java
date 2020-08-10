package com.tmr.domain;

public class Food {
    private int foodId;
    private String foodName;
    private String foodExplain;
    private double foodPrice;
    private int businessId;

    public Food(int foodId, String foodName, String foodExplain, double foodPrice, int businessId) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodExplain = foodExplain;
        this.foodPrice = foodPrice;
        this.businessId = businessId;
    }

    public Food() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodExplain() {
        return foodExplain;
    }

    public void setFoodExplain(String foodExplain) {
        this.foodExplain = foodExplain;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", foodExplain='" + foodExplain + '\'' +
                ", foodPrice=" + foodPrice +
                ", businessId=" + businessId +
                '}';
    }
}
