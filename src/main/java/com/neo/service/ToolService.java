package com.neo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neo.entity.Tool;

public interface ToolService {
    Page<Tool> page(Integer pageNum, Integer pageSize);
    Tool getById(Long id);
    Tool create(Tool tool);
    Tool update(Tool tool);
    void delete(Long id);
    void online(Long id);
    void offline(Long id);
}
