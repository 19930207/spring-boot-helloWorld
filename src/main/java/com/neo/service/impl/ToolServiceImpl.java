package com.neo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neo.entity.Tool;
import com.neo.mapper.ToolMapper;
import com.neo.service.ToolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ToolServiceImpl implements ToolService {

    @Resource
    private ToolMapper toolMapper;

    @Override
    public Page<Tool> page(Integer pageNum, Integer pageSize) {
        Page<Tool> page = new Page<>(pageNum, pageSize);
        return toolMapper.selectPage(page, null);
    }

    @Override
    public Tool getById(Long id) {
        return toolMapper.selectById(id);
    }

    @Override
    public Tool create(Tool tool) {
        tool.setStatus(1);
        toolMapper.insert(tool);
        return tool;
    }

    @Override
    public Tool update(Tool tool) {
        toolMapper.updateById(tool);
        return toolMapper.selectById(tool.getId());
    }

    @Override
    public void delete(Long id) {
        toolMapper.deleteById(id);
    }

    @Override
    public void online(Long id) {
        Tool tool = new Tool();
        tool.setId(id);
        tool.setStatus(1);
        toolMapper.updateById(tool);
    }

    @Override
    public void offline(Long id) {
        Tool tool = new Tool();
        tool.setId(id);
        tool.setStatus(0);
        toolMapper.updateById(tool);
    }
}
