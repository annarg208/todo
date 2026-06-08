package entity;

import java.sql.Date;


public class Todo {
    private int todoId;
    private String title;
    private String detail;
    private String status;
    private Date dueDate;

    // デフォルトコンストラクタ（new Todo()用）
    public Todo() {}

    // 全フィールドあり（todo_idも含む）
    public Todo(int todoId, String title, String detail, String status, Date dueDate) {
        this.todoId = todoId;
        this.title = title;
        this.detail = detail;
        this.status = status;
        this.dueDate = dueDate;
    }

    // todo_idなし（INSERT用）
    public Todo(String title, String detail, String status, Date dueDate) {
        this.title = title;
        this.detail = detail;
        this.status = status;
        this.dueDate = dueDate;
    }

    public int getTodoId() { return todoId; }
    public void setTodoId(int todoId) { this.todoId = todoId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date duedate2) { this.dueDate = duedate2; }
}