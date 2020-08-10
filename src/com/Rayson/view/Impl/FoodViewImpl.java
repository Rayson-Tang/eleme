package com.Rayson.view.Impl;

import com.Rayson.dao.FoodDao;
import com.Rayson.dao.Impl.FoodDaoImpl;
import com.Rayson.domain.Food;
import com.Rayson.view.FoodView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodViewImpl implements FoodView {
    private static Scanner input = new Scanner(System.in);
    FoodDao foodDao = new FoodDaoImpl();

    @Override
    public List<Food> showFoodList(Integer businessId) {
        List<Food> foodList = new ArrayList<>();
        foodList = foodDao.foodList(businessId);
        System.out.println("食物名称\t食物说明\t价格");
        for (Food food : foodList) {
            System.out.println(food.getFoodName() + "\t" + food.getFoodExplain() + "\t" + food.getFoodPrice());
        }
        return foodList;
    }

    @Override
    public void saveFood(Integer businessId) {
        Food food = new Food();
        System.out.println("请输入商品名称");
        food.setFoodName(input.next());
        System.out.println("请输入商品描述");
        food.setFoodExplain(input.next());
        System.out.println("请输入商品价格");
        food.setFoodPrice(input.nextDouble());
        food.setBusinessId(businessId);
        food.setFoodId(0);
        int foodId = foodDao.saveFood(food);
        System.out.println("添加成功");
        System.out.println("食品编号为" + foodId);
    }

    @Override
    public void updateFood(Integer businessId) {
        Integer menu = 0;
        while (menu != 6) {
            System.out.println("请输入食品编号");
            int foodId = input.nextInt();
            System.out.println("请选择要更改的信息 1.名称 2.食品分类 3.价格 输入4退出更改");
            menu = input.nextInt();
            switch (menu) {
                case 1: {
                    System.out.println("请输入新的名称");
                    String newName = input.next();
                    int result = foodDao.updateFood(foodId, "foodName", newName);
                    if (result != 0) {
                        System.out.println("更改成功");
                    }
                    else {
                        System.out.println("更改失败");
                    }
                    break;
                }

                case 2: {
                    System.out.println("请输入新的食品分类");
                    String newExplain = input.next();
                    int result = foodDao.updateFood(foodId, "foodExplain", newExplain);
                    if (result != 0) {
                        System.out.println("更改成功");
                    }
                    else {
                        System.out.println("更改失败");
                    }
                    break;
                }
                case 3: {
                    System.out.println("请输入新的价格");
                    String newPrice = input.next();
                    int result = foodDao.updateFood(foodId, "foodPrice", newPrice);
                    if (result != 0) {
                        System.out.println("更改成功");
                    }
                    else {
                        System.out.println("更改失败");
                    }
                    break;
                }
                case 4: {
                    System.out.println("已退出更改信息模式");
                    break;
                }
                default: {
                    System.out.println("非法输入");
                    break;
                }
            }
        }
    }


    @Override
    public void removeFood(Integer businessId) {
        System.out.println("请输入要删除的食品编号");
        int foodId = input.nextInt();
        System.out.println("是否要删除该食品 y/n");
        if (input.next().equals("y")) {
            int result = foodDao.removeFood(foodId);
            if (result > 0) {
                System.out.println("删除成功");
            }
            else {
                System.out.println("删除失败");
            }
        }
    }
}
