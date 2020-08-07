package com.tmr.view.Impl;

import com.tmr.dao.BusinessDao;
import com.tmr.dao.Impl.BusinessDaoImpl;
import com.tmr.domain.Business;
import com.tmr.view.BusinessView;

import java.util.ArrayList;
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
        int r = businessDao.remove(businessId);
        if (r != 0){
            System.out.println("删除成功");
        }else {
            System.out.println("无此商家");
        }
    }
}
