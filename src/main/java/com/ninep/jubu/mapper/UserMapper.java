package com.ninep.jubu.mapper;

import com.ninep.jubu.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String userId);
    int insert(User record);
    int insertSelective(User record);

    User selectByPrimaryKey(String userId);
    int updateByPrimaryKeySelective(User record);
    int updateByPrimaryKey(User record);

    User getUserByName(String userName);
}