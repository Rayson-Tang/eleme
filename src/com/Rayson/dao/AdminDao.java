package com.Rayson.dao;

import com.Rayson.domain.Admin;

public interface AdminDao {
    public Admin getAdminByNameByPassword(String adminName, String password);
}
