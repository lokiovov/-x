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
			System.err.println("�Ҳ���ORACLE��������ϵͳ�޷������������˳�");
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
			System.err.println("���ݿ����Ӵ���ϵͳ�޷������������˳�");
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}	*/	
	}
}
