package services;

import java.util.ArrayList;
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
            System.out.println("List is empty there are no tasks");
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
}
