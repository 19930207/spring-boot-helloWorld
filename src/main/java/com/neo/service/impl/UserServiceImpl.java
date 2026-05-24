package com.neo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neo.entity.User;
import com.neo.mapper.UserMapper;
import com.neo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Page<User> page(Integer pageNum, Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        return userMapper.selectPage(page, null);
    }

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void enable(Long id) {
        User user = new User();
        user.setId(id);
        user.setStatus(1);
        userMapper.updateById(user);
    }

    @Override
    public void disable(Long id) {
        User user = new User();
        user.setId(id);
        user.setStatus(0);
        userMapper.updateById(user);
    }
}
