package com.neo.service;

import com.neo.entity.Todo;
import java.util.List;

public interface TodoService {
    List<Todo> list(Boolean completed);
    Todo getById(Long id);
    Todo create(Todo todo);
    Todo update(Todo todo);
    void delete(Long id);
    Todo toggle(Long id);
    List<Todo> reorder(List<Long> ids);
}
