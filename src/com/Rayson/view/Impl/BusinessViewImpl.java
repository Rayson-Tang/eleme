package com.Rayson.view.Impl;

import com.Rayson.dao.BusinessDao;
import com.Rayson.dao.Impl.BusinessDaoImpl;
import com.Rayson.domain.Business;
import com.Rayson.view.BusinessView;

import java.util.List;
import java.util.Scanner;

public class BusinessViewImpl implements BusinessView {
    BusinessDao businessDao = new BusinessDaoImpl();
    Scanner input = new Scanner(System.in);


    @Override
    public void searchAll() {
        List<Business> listAll = businessDao.search(null, null);
        System.out.println("商家编号\t商家名称\t商家地址\t食品分类\t起送价格\t配送价格");
        for (Business business : listAll) {
            System.out.println(business.getBusinessId() + "\t" + business.getBusinessName() + "\t" + business.getBusinessAddress() + "\t" + business.getBusinessExplain() + "\t" + business.getStarPrice() + "\t" + business.getDeliverPrice());
        }
    }

    @Override
    public void search() {
        String businessName = null;
        String businessAddress = null;
        System.out.println("是否要输入查询商家名称（y/n）");
        String str = input.next();
        if (str.equals("y")) {
            System.out.println("请输入商家关键字");
            businessName = input.next();
        }
        System.out.println("是否要输入查询商家地址（y/n）");
        str = input.next();
        if (str.equals("y")) {
            System.out.println("请输入商家地址关键字");
            businessAddress = input.next();
        }
        List<Business> listAll = businessDao.search(businessName, businessAddress);
        System.out.println("商家编号\t商家名称\t商家地址\t食品分类\t起送价格\t配送价格");
        for (Business business : listAll) {
            System.out.println(business.getBusinessId() + "\t" + business.getBusinessName() + "\t" + business.getBusinessAddress() + "\t" + business.getBusinessExplain() + "\t" + business.getStarPrice() + "\t" + business.getDeliverPrice());
        }
    }

    @Override
    public void create() {
        Business business = new Business();
        System.out.println("请输入商家名称");
        String businessName = input.next();
        business.setBusinessName(businessName);

        System.out.println("请输入商家地址");
        String businessAddress = input.next();
        business.setBusinessAddress(businessAddress);

        System.out.println("请输入食品分类");
        String businessExplain = input.next();
        business.setBusinessExplain(businessExplain);

        System.out.println("请输入起送价格");
        Double starPrice = input.nextDouble();
        business.setStarPrice(starPrice);

        System.out.println("请输入配送价格");
        Double deliverPrice = input.nextDouble();
        business.setDeliverPrice(deliverPrice);

        business.setBusinessId(0);

        int Id = businessDao.save(business);
        System.out.println("您的商家编号为" + Id);
    }

    @Override
    public void delete() {
        System.out.println("请输入要删除的商家编号");
        int businessId = input.nextInt();
        System.out.println("是否要删除该商家 y/n");
        if (input.next().equals("y")) {
            int r = businessDao.remove(businessId);
            if (r > 0) {
                System.out.println("删除成功");
            }
            else {
                System.out.println("无此商家");
            }
        }
    }

    @Override
    public Business login() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入商家编号");
        Integer BusinessId = input.nextInt();
        System.out.println("请输入密码");
        String password = input.next();
        BusinessDao businessDao = new BusinessDaoImpl();
        Business business = new Business();
        business = businessDao.getBusinessByNameByPassword(BusinessId, password);
        return business;
    }

    @Override
    public void BusinessMsg(Business business) {
        List<Business> list = businessDao.lookMsg(business.getBusinessId(), business.getPassword());
        for (Business b : list) {
            System.out.println("商家编号：" + b.getBusinessId());
            System.out.println("商家名称：" + b.getBusinessName());
            System.out.println("商家地址：" + b.getBusinessAddress());
            System.out.println("食品分类：" + b.getBusinessExplain());
            System.out.println("起送价格：" + b.getStarPrice());
            System.out.println("配送价格：" + b.getDeliverPrice());
        }
    }

    @Override
    public void reviseMsg(Integer businessId) {
        Integer menu = 0;
        while (menu != 6) {
            System.out.println("请选择要更改的信息 1.名称 2.地址 3.食品分类 4.起送价格 5.配送价格，输入6退出更改");
            menu = input.nextInt();
            switch (menu) {
                case 1: {
                    System.out.println("请输入新的名称");
                    String newName = input.next();
                    int result = businessDao.updateMsg(businessId, "businessName", newName);
                    if (result != 0) {
                        System.out.println("更改成功");
                    }
                    else {
                        System.out.println("更改失败");
                    }
                    break;
                }
                case 2: {
                    System.out.println("请输入新的地址");
                    String newAddress = input.next();
                    int result = businessDao.updateMsg(businessId, "businessAddress", newAddress);
                    if (result != 0) {
                        System.out.println("更改成功");
                    }
                    else {
                        System.out.println("更改失败");
                    }
                    break;
                }
                case 3: {
                    System.out.println("请输入新的食品分类");
                    String newExplain = input.next();
                    int result = businessDao.updateMsg(businessId, "businessExplain", newExplain);
                    if (result != 0) {
                        System.out.println("更改成功");
                    }
                    else {
                        System.out.println("更改失败");
                    }
                    break;
                }
                case 4: {
                    System.out.println("请输入新的起送价格");
                    String newStarPrice = input.next();
                    int result = businessDao.updateMsg(businessId, "starPrice", newStarPrice);
                    if (result != 0) {
                        System.out.println("更改成功");
                    }
                    else {
                        System.out.println("更改失败");
                    }
                    break;
                }
                case 5: {
                    System.out.println("请输入新的配送价格");
                    String newDeliverPrice = input.next();
                    int result = businessDao.updateMsg(businessId, "deliverPrice", newDeliverPrice);
                    if (result != 0) {
                        System.out.println("更改成功");
                    }
                    else {
                        System.out.println("更改失败");
                    }
                    break;
                }
                case 6: {
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
    public void revisePassword(Integer businessId) {
        System.out.println("请输入原密码");
        String oldPassword1 = input.next();
        String oldPassword2 = businessDao.confirmPassword(businessId, oldPassword1);
        if (oldPassword1 == oldPassword2 || oldPassword1.equals(oldPassword2)) {
            System.out.println("请输入新密码");
            String newPassword1 = input.next();
            System.out.println("再次输入新密码");
            String newPassword2 = input.next();
            if (newPassword1 == newPassword2 || oldPassword1.equals(oldPassword2)) {
                businessDao.updatePassword(businessId, newPassword1);
                System.out.println("更改成功");
            }
            else {
                System.out.println("两次密码不一致");
            }
        }
        else {
            System.out.println("原密码错误");
        }
    }
}
