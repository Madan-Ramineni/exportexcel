package com.excel.exportexcel.service.impl;

import com.excel.exportexcel.model.UserDetails;
import com.excel.exportexcel.repository.UserRepository;
import com.excel.exportexcel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails saveUser(UserDetails user) throws Exception {
        UserDetails userDetails;
        userDetails = userRepository.findByName(user.getName());
        if (null != userDetails) {
            throw new Exception("User name " + user.getName() + " already exists. Please use different user name");
        } else {
            userDetails = userRepository.save(user);
        }
        return userDetails;
    }

    @Override
    public UserDetails validateUser(UserDetails user) throws Exception {
        UserDetails userDetails = userRepository.findByName(user.getName());
        if (null != userDetails) {
            return userDetails;
        } else {
            throw new Exception("Invalid User " + user.getName() + ", Please register before login");
        }
    }
}
