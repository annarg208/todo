package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Todo;
import utils.DbUtil;

public class TodoDao {

	// 全件取得
	public ArrayList<Todo> findAll() throws SQLException {
		String sql = "SELECT * FROM todos ORDER BY todo_id";
		ArrayList<Todo> result = new ArrayList<>();

		try (Connection conn = DbUtil.open();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Todo t = new Todo();
				t.setTodoId(rs.getInt("todo_id"));
				t.setTitle(rs.getString("title"));
				t.setDetail(rs.getString("detail"));
				t.setStatus(rs.getString("status"));
				Date d = rs.getDate("due_date");
				if (d != null) {
					t.setDueDate(d);
				}
				result.add(t);
			}
		}
		return result;
	}

	// 新規登録
	public void create(Todo todo) throws SQLException {
		String sql = "INSERT INTO todos (title, detail, status, due_date) VALUES (?, ?, ?, ?)";
		try (Connection conn = DbUtil.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, todo.getTitle());
			pstmt.setString(2, todo.getDetail());
			pstmt.setString(3, todo.getStatus());
			if (todo.getDueDate() != null) {
				pstmt.setDate(4, todo.getDueDate());
			} else {
				pstmt.setNull(4, java.sql.Types.DATE);
			}
			pstmt.executeUpdate();
		}
	}

	// キーワード・ステータスで絞り込み検索（追加課題A・B・C対応）
	public ArrayList<Todo> findBySearch(String keyword, String status) throws SQLException {

		// 条件によってSQLを動的に組み立てる
		StringBuilder sql = new StringBuilder("SELECT * FROM todos WHERE 1=1");

		if (keyword != null && !keyword.isEmpty()) {
			sql.append(" AND (title LIKE ? OR detail LIKE ?)");
		}
		if (status != null && !status.equals("all") && !status.isEmpty()) {
			sql.append(" AND status = ?");
		}
		sql.append(" ORDER BY todo_id");

		ArrayList<Todo> result = new ArrayList<>();

		try (Connection conn = DbUtil.open();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

			// ?に値をセット
			int idx = 1;
			if (keyword != null && !keyword.isEmpty()) {
				pstmt.setString(idx++, "%" + keyword + "%");
				pstmt.setString(idx++, "%" + keyword + "%");
			}
			if (status != null && !status.equals("all") && !status.isEmpty()) {
				pstmt.setString(idx++, status);
			}

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Todo t = new Todo();
					t.setTodoId(rs.getInt("todo_id"));
					t.setTitle(rs.getString("title"));
					t.setDetail(rs.getString("detail"));
					t.setStatus(rs.getString("status"));
					Date d = rs.getDate("due_date");
					if (d != null) {
						t.setDueDate(d);
					}
					result.add(t);
				}
			}
		}
		return result;
	}

	// 削除
	public void delete(int todoId) throws SQLException {
		String sql = "DELETE FROM todos WHERE todo_id = ?";

		try (Connection conn = DbUtil.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, todoId);
			pstmt.executeUpdate();
		}
	}

	// 更新
	public void update(Todo todo) throws SQLException {
		String sql = "UPDATE todos SET title=?, detail=?, status=?, due_date=? WHERE todo_id=?";

		try (Connection conn = DbUtil.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, todo.getTitle());
			pstmt.setString(2, todo.getDetail());
			pstmt.setString(3, todo.getStatus());
			if (todo.getDueDate() != null) {
				pstmt.setDate(4, todo.getDueDate());
			} else {
				pstmt.setNull(4, java.sql.Types.DATE);
			}
			pstmt.setInt(5, todo.getTodoId());

			pstmt.executeUpdate();
		}
	}
	
	// IDで1件取得
	public Todo findById(int todoId) throws SQLException {
	    String sql = "SELECT * FROM todos WHERE todo_id = ?";

	    try (Connection conn = DbUtil.open();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setInt(1, todoId);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                Todo t = new Todo();
	                t.setTodoId(rs.getInt("todo_id"));
	                t.setTitle(rs.getString("title"));
	                t.setDetail(rs.getString("detail"));
	                t.setStatus(rs.getString("status"));
	                Date d = rs.getDate("due_date");
	                if (d != null) {
	                    t.setDueDate(d);
	                }
	                return t;
	            }
	        }
	    }
	    return null;
	}
}