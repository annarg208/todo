package service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.TodoDao;
import entity.Todo;

public class TodoService {

    public ArrayList<Todo> getTodos() {
        ArrayList<Todo> result = null;
        try {
            result = new TodoDao().findAll();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return result;
    }

    // 追加課題A・B・C対応：キーワード・ステータスで絞り込み
    public ArrayList<Todo> searchTodos(String keyword, String status) {
        ArrayList<Todo> result = null;
        try {
            result = new TodoDao().findBySearch(keyword, status);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return result;
    }
    
    

    public void createTodo(String title, String detail, String status, String duedate) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDetail(detail);
        todo.setStatus(status);
        if (duedate != null && !duedate.isEmpty()) {
            todo.setDueDate(Date.valueOf(duedate));
        }
        try {
            new TodoDao().create(todo);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    public void deleteTodo(int todoId) {
        try {
            new TodoDao().delete(todoId);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        
    }
      public void update(Todo todo) {
            try {
                new TodoDao().update(todo);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        
    }
 // IDで1件取得
    public Todo getTodoById(int todoId) {
        try {
            return new TodoDao().findById(todoId);
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }

    // 更新（Controllerから呼ぶ）
    public void updateTodo(int todoId, String title, String detail, String status, String duedate) {
        Todo todo = new Todo();
        todo.setTodoId(todoId);
        todo.setTitle(title);
        todo.setDetail(detail);
        todo.setStatus(status);
        if (duedate != null && !duedate.isEmpty()) {
            todo.setDueDate(Date.valueOf(duedate));
        }
        update(todo);  // ← すでにあるupdate(Todo todo)を呼ぶ
    }
}