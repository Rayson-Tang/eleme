package com.tmr;

import com.tmr.domain.Admin;
import com.tmr.domain.Business;
import com.tmr.view.AdminView;
import com.tmr.view.BusinessView;
import com.tmr.view.FoodView;
import com.tmr.view.Impl.AdminViewImpl;
import com.tmr.view.Impl.BusinessViewImpl;
import com.tmr.view.Impl.FoodViewImpl;

import java.util.Scanner;

public class ElmBusiness {


    public static void main(String[] args) {
        work();
    }

    public static void work() {
        Scanner input = new Scanner(System.in);
        BusinessView businessView = new BusinessViewImpl();
        Business business = new Business();
        System.out.println("-------------------------------------------------------");
        System.out.println("\t\t\t\t|饿了么管理系统|\t\t\t\t");
        System.out.println("-------------------------------------------------------");
        business = businessView.login();
        if (business != null) {
            System.out.println("登录成功");
            System.out.println("~欢迎来到饿了么商家管理系统~");
            int menu = 0;
            while (menu != 5) {
                System.out.println("=========一级菜单： 1.查看商家信息=2.修改商家信息=3.更改密码=4.所属食品管理=5.退出系统 =========");
                System.out.println("请输入相应菜单编号");
                menu = input.nextInt();
                switch (menu) {
                    case 1: {
                        businessView.BusinessMsg(business);
                        break;
                    }
                    case 2: {
                        businessView.reviseMsg(business.getBusinessId());
                        break;
                    }
                    case 3: {
                        businessView.revisePassword(business.getBusinessId());
                        break;
                    }
                    case 4: {
                        foodManage(business.getBusinessId());
                        break;
                    }
                    case 5: {
                        System.out.println("欢迎下次光临");
                        break;
                    }
                }
            }
        }
        else {
            System.out.println("用户名或密码错误");
        }
    }

    private static void foodManage(int businessId) {
        Scanner input = new Scanner(System.in);
        FoodView foodView = new FoodViewImpl();
        int menu = 0;
        while (menu != 5) {
            System.out.println("=========二级菜单： 1.查看食品列表=2.新增食品=3.修改食品=4.删除食品=5.返回一级菜单 =========");
            System.out.println("请输入相应菜单编号");
            menu = input.nextInt();
            switch (menu) {
                case 1: {
                    foodView.showFoodList(businessId);
                    break;
                }
                case 2: {
                    foodView.saveFood(businessId);
                    break;
                }
                case 3: {
                    foodView.updateFood(businessId);
                    break;
                }
                case 4: {
                    foodView.removeFood(businessId);
                    break;
                }
                case 5: {
                    System.out.println("返回一级菜单");
                    break;
                }
                default: {
                    System.out.println("非法输入");
                    break;
                }
            }
        }
    }
}