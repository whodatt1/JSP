package hotel_teacher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnect {
	private static MySQLConnect dbc = new MySQLConnect();
	private Connection conn = null;
	private String jdbcDriver = "com.mysql.jdbc.Driver"; //oracle.jdbc.driver.OracleDriver
	private String jdbcUrl = "jdbc:mysql://localhost/javatest"; // jdbc:oracle:thin:@localhost:1521:orcl
	
	private MySQLConnect() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcUrl, "root", "mysql");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다"); // 라이브러리 연결 실패
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("연결 정보가 정확하지 않습니다");
			e.printStackTrace();
		}
	}
	public static MySQLConnect getInstance() {
		return dbc;
	}
	public Connection getConnection() {
		return conn;
	}
}
