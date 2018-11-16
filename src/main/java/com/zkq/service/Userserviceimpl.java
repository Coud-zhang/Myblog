package com.zkq.service;
import com.zkq.mapper.UserMapper;
import com.zkq.domain.UsersCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author zkq15
 * */
@Service
public class Userserviceimpl implements Userservice {
        @Autowired
        UserMapper userMapper;
@Override
public boolean checkUserNameAndPassword(UsersCustom usersCustom) {
        return userMapper.checkUserByUserNameAndPassword(usersCustom)==1?true:false;
        }
}

