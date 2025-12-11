package services;

import java.util.ArrayList;
import java.util.List;

import models.Todo;

public class TodoService {
    ArrayList<Todo> todoList = new ArrayList<>();

    int todoListCounter = 0;
    public void createTodo(String title) {
        todoListCounter++;
        Todo todoTask = new Todo(todoListCounter, false, title);
        todoList.add(todoTask);
    }

    public List<Todo> readAllTodo() {
        if (todoList.isEmpty()) {
            return todoList;
        } else {
            return todoList;
        }
    }

    public Todo readSpecificTodo(int id) {
        for (Todo e: todoList) {
            if (id == e.getId()) {
                return e;
            }
        }
        return null;
    }

    public Todo updateTodo(int id, String title, boolean isCompleted) {
        for (Todo e: todoList) {
            if (id == e.getId()) {
                e.setTitle(title);;
                e.setIsCompleted(isCompleted);
                return e;
            }
        }
        return null;
    }

    public boolean removeTodo(int id) {
        return todoList.removeIf(e -> e.getId() == id);
    }
}
