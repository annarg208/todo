package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entity.Todo;
import service.TodoService;

@WebServlet("/TodoList")
public class TodoList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // フォームからキーワードとステータスを取得
        String keyword = request.getParameter("keyword");
        String status  = request.getParameter("status");

        TodoService service = new TodoService();
        ArrayList<Todo> todos;

        // キーワードもステータスも未入力なら全件取得、そうでなければ検索
        if ((keyword == null || keyword.isEmpty()) &&
            (status == null || status.equals("all") || status.isEmpty())) {
            todos = service.getTodos();
        } else {
            todos = service.searchTodos(keyword, status);
        }

        // JSPに渡す
        request.setAttribute("todos", todos);
        request.setAttribute("keyword", keyword);  // 検索後も入力値を保持
        request.setAttribute("status", status);    // 検索後も選択値を保持

        request.getRequestDispatcher("/todo_list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}