package com.zkq.Mapper;

import com.zkq.domain.UsersCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    public int checkUserByUserNameAndPassword(UsersCustom usersCustom);
}
