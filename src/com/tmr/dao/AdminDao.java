package com.tmr.dao;

import com.tmr.domain.Admin;

public interface AdminDao {
    public Admin getAdminByNameByPassword(String adminName, String password);
}
