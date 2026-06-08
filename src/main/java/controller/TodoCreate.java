package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.TodoService;

@WebServlet("/TodoCreate")
public class TodoCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TodoCreate() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/todo_create.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");
		String status = request.getParameter("status");
		String duedate = request.getParameter("due_date");
		
		TodoService service = new TodoService();
		service.createTodo(title, detail, status, duedate);
		
		response.sendRedirect(request.getContextPath() + "/TodoList");
	}

}
