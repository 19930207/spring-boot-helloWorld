package com.neo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neo.entity.User;

public interface UserService {
    Page<User> page(Integer pageNum, Integer pageSize);
    User getById(Long id);
    void enable(Long id);
    void disable(Long id);
}
