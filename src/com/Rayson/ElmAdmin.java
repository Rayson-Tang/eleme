package com.Rayson;

import com.Rayson.domain.Admin;
import com.Rayson.view.AdminView;
import com.Rayson.view.BusinessView;
import com.Rayson.view.Impl.AdminViewImpl;
import com.Rayson.view.Impl.BusinessViewImpl;

import java.util.Scanner;

public class ElmAdmin {
    public static void main(String[] args) {
        work();
    }

    public static void work() {
        BusinessView businessView = new BusinessViewImpl();
        Admin admin = new Admin();
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------");
        System.out.println("\t\t\t\t|饿了么管理系统|\t\t\t\t");
        System.out.println("-------------------------------------------------------");
        AdminView adminView = new AdminViewImpl();
        admin = adminView.login();
        if (admin != null) {
            System.out.println("登录成功");
            System.out.println("~欢迎来到饿了么商家管理系统~");
            int menu = 0;
            while (menu != 5) {
                System.out.println("========= 1.所有商家列表=2.搜索商家=3.新建商家=4.删除商家=5.退出系统 =========");
                System.out.println("请输入相应菜单编号");
                menu = input.nextInt();
                switch (menu) {
                    case 1: {
                        businessView.searchAll();
                        break;
                    }
                    case 2: {
                        businessView.search();
                        break;
                    }
                    case 3: {
                        businessView.create();
                        break;
                    }
                    case 4: {
                        businessView.delete();
                        break;
                    }
                    case 5: {
                        System.out.println("欢迎下次光临");
                        break;
                    }
                    default: {
                        System.out.println("非法输入");
                        break;
                    }
                }
            }
        }
        else {
            System.out.println("用户名或密码错误");
        }
    }
}
