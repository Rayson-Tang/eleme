package com.tmr.dao;

import com.tmr.domain.Business;

import java.util.List;

public interface BusinessDao {
    public List<Business> search(String businessName, String businessAddress);

    public Integer save(Business business);

    public int remove(Integer businessId);
}
