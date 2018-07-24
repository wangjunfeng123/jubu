package com.ninep.jubu.mapper;

import com.ninep.jubu.domain.Auth;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    Auth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    Auth getByUrl(String url);
}