package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entity.Todo;
import service.TodoService;

@WebServlet("/TodoEdit")
public class TodoEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GET → 編集フォームを表示
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // todo_idで対象データを取得
        int todoId = Integer.parseInt(request.getParameter("todoId"));
        TodoService service = new TodoService();
        Todo todo = service.getTodoById(todoId);

        request.setAttribute("todo", todo);
        request.getRequestDispatcher("/todo_edit.jsp").forward(request, response);
    }

    // POST → 更新処理
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int todoId   = Integer.parseInt(request.getParameter("todoId"));
        String title  = request.getParameter("title");
        String detail = request.getParameter("detail");
        String status = request.getParameter("status");
        String duedate = request.getParameter("due_date");

        TodoService service = new TodoService();
        service.updateTodo(todoId, title, detail, status, duedate);

        response.sendRedirect(request.getContextPath() + "/TodoList");
    }
}