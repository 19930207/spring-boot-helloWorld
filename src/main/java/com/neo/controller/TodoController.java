package com.neo.controller;

import com.neo.common.Result;
import com.neo.entity.Todo;
import com.neo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Resource
    private TodoService todoService;

    @GetMapping
    public Result<List<Todo>> list(@RequestParam(required = false) Boolean completed) {
        return Result.success(todoService.list(completed));
    }

    @GetMapping("/{id}")
    public Result<Todo> getById(@PathVariable Long id) {
        Todo todo = todoService.getById(id);
        if (todo == null) {
            return Result.error("待办不存在");
        }
        return Result.success(todo);
    }

    @PostMapping
    public Result<Todo> create(@RequestBody Todo todo) {
        return Result.success(todoService.create(todo));
    }

    @PutMapping("/{id}")
    public Result<Todo> update(@PathVariable Long id, @RequestBody Todo todo) {
        todo.setId(id);
        return Result.success(todoService.update(todo));
    }

    @PutMapping("/{id}/toggle")
    public Result<Todo> toggle(@PathVariable Long id) {
        return Result.success(todoService.toggle(id));
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        todoService.delete(id);
        return Result.success();
    }

    @PutMapping("/reorder")
    public Result<List<Todo>> reorder(@RequestBody Map<String, List<Long>> body) {
        return Result.success(todoService.reorder(body.get("ids")));
    }
}
