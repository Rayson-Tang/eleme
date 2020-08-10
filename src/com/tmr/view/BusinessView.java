package com.tmr.view;

import com.tmr.domain.Business;

public interface BusinessView {
    public void searchAll();

    public void search();

    public void create();

    public void delete();

    //商家登录验证
    public Business login();

    public void BusinessMsg(Business business);

    public void reviseMsg(Integer businessId);

    public void revisePassword(Integer businessId);
}
