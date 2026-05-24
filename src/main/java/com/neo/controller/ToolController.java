package com.neo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neo.common.Result;
import com.neo.entity.Tool;
import com.neo.service.ToolService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/tools")
public class ToolController {

    @Resource
    private ToolService toolService;

    @GetMapping
    public Result<Page<Tool>> list(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(toolService.page(pageNum, pageSize));
    }

    @GetMapping("/{id}")
    public Result<Tool> getById(@PathVariable Long id) {
        Tool tool = toolService.getById(id);
        if (tool == null) {
            return Result.error("工具不存在");
        }
        return Result.success(tool);
    }

    @PostMapping
    public Result<Tool> create(@RequestBody Tool tool) {
        return Result.success(toolService.create(tool));
    }

    @PutMapping("/{id}")
    public Result<Tool> update(@PathVariable Long id, @RequestBody Tool tool) {
        tool.setId(id);
        return Result.success(toolService.update(tool));
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        toolService.delete(id);
        return Result.success();
    }

    @PutMapping("/{id}/online")
    public Result<Void> online(@PathVariable Long id) {
        toolService.online(id);
        return Result.success();
    }

    @PutMapping("/{id}/offline")
    public Result<Void> offline(@PathVariable Long id) {
        toolService.offline(id);
        return Result.success();
    }
}
