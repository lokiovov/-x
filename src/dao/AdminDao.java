package dao;

import java.util.List;

import entities.Admin;

public interface AdminDao {
	public Admin add(Admin a);
	public boolean del(Admin a);
	public Admin upd(Admin a);
	public List<Admin> getByWhere(String where);
	public Admin getById(int id);
	public List<Admin> getByUsername(String username);
	public List<Admin> getByPassword(String password);

}
