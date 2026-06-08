package utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtil {
	public static Connection open() {

		Connection conn = null;

		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/mariadb");
			conn = ds.getConnection();
		} catch (NamingException ne) {
			System.err.println("コンテキストの取得に失敗");
		} catch (SQLException se) {
			System.err.println("データベース接続に失敗");
		}

		return conn;
	}

	public static void close(Connection con) {
		try {
			con.close();			
		} catch (SQLException se) {
			System.err.println("データベース切断に失敗");
		}
	}
}

