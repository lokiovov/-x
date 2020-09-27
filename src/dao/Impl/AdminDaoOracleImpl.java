package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import dao.AdminDao;
import entities.Admin;

public class AdminDaoOracleImpl implements AdminDao {

	@Override
	public Admin add(Admin a) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"insert into admin values(req.nextval, ?, ?)");
			pstmt.setString(1, a.getUsername());
			pstmt.setString(2, a.getPassword());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(
					"select max(id) as id from admin");
			ResultSet rs= pstmt.executeQuery();
			rs.next();
			a.setId(rs.getInt("id"));			
			
			return a;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);	
		}
		return null;
	}

	@Override
	public boolean del(Admin a) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public Admin upd(Admin a) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<Admin> getByWhere(String where) {
		List<Admin> list = new ArrayList<Admin>();
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"select * from admin where " + where);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Admin a = new Admin(id, username, password);
				list.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Admin getById(int id) {
		List<Admin> list =   this.getByWhere(" id = " + id);
		if(list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<Admin> getByUsername(String username) {
		return this.getByWhere("username= '" + username +"'");
	}

	@Override
	public List<Admin> getByPassword(String password) {
		return this.getByWhere("password= '" + password +"'");
	}

}
