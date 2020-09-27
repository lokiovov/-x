package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import dao.StaffDao;
import entities.Admin;
import entities.Staff;

public class StaffDaoOracleImpl implements StaffDao {

	@Override
	public Staff add(Staff a) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"insert into staff values(req.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, a.getUsername());
			pstmt.setString(2, a.getPassword());
			pstmt.setString(3, a.getName());
			pstmt.setInt(4, a.getIdcard());
			pstmt.setInt(5, a.getTel());
			pstmt.setString(6, a.getDept());
			pstmt.setString(7, a.getJob());
			pstmt.setInt(8, a.getAdminid());
			
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(
					"select max(id) as id from staff");
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
	public boolean del(Staff a) {
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"delete from staff where id=?");
			pstmt.setInt(1, a.getId());
			pstmt.executeUpdate();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		return false;
	 }
  }

	@Override
	public Staff upd(Staff a) {
		Connection conn = DBUtil.getConnection();
		try {			
			PreparedStatement pstmt = conn.prepareStatement(
					"update staff set username=?,password=?,name=?,idcard=?,tel=?,dept=?,job=?,adminid=? where id=?");
			pstmt.setString(1, a.getUsername());
			pstmt.setString(2, a.getPassword());
			pstmt.setString(3, a.getName());
			pstmt.setInt(4, a.getIdcard());
			pstmt.setInt(5, a.getTel());
			pstmt.setString(6, a.getDept());
			pstmt.setString(7, a.getJob());
			pstmt.setInt(8, a.getAdminid());
			pstmt.setInt(9, a.getId());
			pstmt.executeUpdate();
			return a;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn);
		}
		return null;
	}

	@Override
	public List<Staff> getByWhere(String where) {
		List<Staff> list = new ArrayList<Staff>();
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"select * from staff where " + where);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int idcard = rs.getInt("idcard");
                int tel = rs.getInt("tel");
				String dept = rs.getString("dept");
				String job = rs.getString("job");
				int adminid = rs.getInt("adminid");
				Staff a = new Staff(id, username, password,name,idcard,tel,dept,job,adminid);
				list.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Staff getById(int id) {
		List<Staff> list =   this.getByWhere(" id = " + id);
		if(list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}
	

	@Override
	public List<Staff> getByUsername(String username) {
		return this.getByWhere("username= '" + username +"'");
	}

	@Override
	public List<Staff> getByPassword(String password) {
		return this.getByWhere("password= '" + password +"'");
	}

	@Override
	public List<Staff> getByid(String where) {
		return this.getByWhere(where);
	}

	@Override
	public Staff getByid1(int id) {
		List<Staff> list = this.getByWhere(" id = " + id);
		if(list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}
}
