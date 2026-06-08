package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.TodoService;

/**
 * Servlet implementation class TodoDelete
 */
@WebServlet("/TodoDelete")
public class TodoDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TodoDelete() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int todoId = Integer.parseInt(request.getParameter("todoId"));
		
		TodoService service = new TodoService();
		service.deleteTodo(todoId);
		
		response.sendRedirect(request.getContextPath() + "/TodoList");
	}

}
