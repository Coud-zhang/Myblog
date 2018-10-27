package com.zkq.service;

import com.zkq.Mapper.UserMapper;
import com.zkq.domain.UsersCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userserviceimpl implements userservice {
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean checkUserNameAndPassword(UsersCustom usersCustom) {
        return userMapper.checkUserByUserNameAndPassword(usersCustom)==1?true:false;
    }
}
