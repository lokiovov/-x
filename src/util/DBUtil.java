package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	static {		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.err.println("找不到ORACLE驱动错误，系统无法继续工作，退出");
			System.exit(-1);
		}
	}
	public static Connection getConnection() {
		Connection conn = local.get();		
		try {
			if(conn == null || conn.isClosed()) {				
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "nzy", "123");
				local.set(conn);
			}
			return conn;
		} catch (Exception e) {
			System.err.println("数据库连接错误，系统无法继续工作，退出");
			System.exit(-1);
		}
		return null;
	}
	public static TransactionManager getTransactionManager() {
		TransactionManager tm = new TransactionManager(getConnection());
		return tm;
	}
	public static void close(Connection conn) {
		/*try {
			conn.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}	*/	
	}
}
