package com.zkq.mapper;

import com.zkq.domain.UsersCustom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void checkUserByUserNameAndPassword() {
        UsersCustom usersCustom=new UsersCustom();
        usersCustom.setUsername("zkq162534");
        usersCustom.setPassword("zkq162534");
        int a=userMapper.checkUserByUserNameAndPassword(usersCustom);
        System.out.println(a);
    }
}