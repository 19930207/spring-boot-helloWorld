package com.neo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neo.entity.Todo;
import com.neo.mapper.TodoMapper;
import com.neo.service.TodoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Resource
    private TodoMapper todoMapper;

    @Override
    public List<Todo> list(Boolean completed) {
        LambdaQueryWrapper<Todo> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Todo::getCreateTime);
        if (completed != null) {
            wrapper.eq(Todo::getCompleted, completed ? 1 : 0);
        }
        return todoMapper.selectList(wrapper);
    }

    @Override
    public Todo getById(Long id) {
        return todoMapper.selectById(id);
    }

    @Override
    public Todo create(Todo todo) {
        if (todo.getPriority() == null) {
            todo.setPriority(0);
        }
        if (todo.getCompleted() == null) {
            todo.setCompleted(0);
        }
        todoMapper.insert(todo);
        return todo;
    }

    @Override
    public Todo update(Todo todo) {
        todoMapper.updateById(todo);
        return todoMapper.selectById(todo.getId());
    }

    @Override
    public void delete(Long id) {
        todoMapper.deleteById(id);
    }

    @Override
    public Todo toggle(Long id) {
        Todo todo = todoMapper.selectById(id);
        if (todo == null) {
            throw new RuntimeException("待办不存在");
        }
        todo.setCompleted(todo.getCompleted() == 1 ? 0 : 1);
        todoMapper.updateById(todo);
        return todo;
    }

    @Override
    public List<Todo> reorder(List<Long> ids) {
        for (int i = 0; i < ids.size(); i++) {
            Todo todo = new Todo();
            todo.setId(ids.get(i));
            todo.setPriority(i);
            todoMapper.updateById(todo);
        }
        return todoMapper.selectList(null);
    }
}
