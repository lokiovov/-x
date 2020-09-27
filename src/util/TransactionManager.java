package util;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
	private Connection conn;
	public TransactionManager(Connection conn) {
		this.conn = conn;
	}
	public void beginTrans() throws SQLException {
		this.conn.setAutoCommit(false);
	}
	public void commitTrans() throws SQLException {
		this.conn.commit();
		this.conn.close();
	}
	public void rollback() throws SQLException {
		this.conn.rollback();
		this.conn.close();
	}
	
}
