package dao;

import java.util.List;

import entities.Staff;

public interface StaffDao {
	public Staff add(Staff a);
	public boolean del(Staff a);
	public Staff upd(Staff a);
	public List<Staff> getByWhere(String where);
	public Staff getById(int id);
	public List<Staff> getByUsername(String username);
	public List<Staff> getByPassword(String password);
	public List<Staff> getByid(String where);
	public Staff getByid1(int id);
	

}
