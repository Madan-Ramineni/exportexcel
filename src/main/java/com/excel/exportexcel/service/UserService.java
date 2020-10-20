package com.excel.exportexcel.service;

import com.excel.exportexcel.model.UserDetails;

public interface UserService {
    public UserDetails saveUser(UserDetails user) throws Exception;

    UserDetails validateUser(UserDetails user) throws Exception;
}
