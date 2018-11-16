package com.zkq.mapper;

import com.zkq.domain.UsersCustom;
import org.springframework.stereotype.Repository;
/**
 * @author zkq15
 * @param
 * */
@Repository
public interface UserMapper {
    /**进行用户名和密码校验*/
    public int checkUserByUserNameAndPassword(UsersCustom usersCustom);
}
