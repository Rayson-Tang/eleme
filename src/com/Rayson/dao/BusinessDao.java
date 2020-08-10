package com.Rayson.dao;

import com.Rayson.domain.Business;

import java.util.List;

public interface BusinessDao {
    public List<Business> search(String businessName, String businessAddress);

    public Integer save(Business business);

    public int remove(Integer businessId);

    public Business getBusinessByNameByPassword(Integer BusinessId, String password);

    public List<Business> lookMsg(Integer businessId, String password);

    public Integer updateMsg(Integer Id, String column, String newMsg);

    public Integer updatePassword(Integer Id, String newPassword);

    public String confirmPassword(Integer Id, String oldPassword);
}
