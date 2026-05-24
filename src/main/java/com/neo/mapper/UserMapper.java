package com.neo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
