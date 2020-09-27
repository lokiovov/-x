package manager;

import java.util.List;

import dao.AdminDao;
import dao.StaffDao;
import dao.Impl.AdminDaoOracleImpl;
import dao.Impl.StaffDaoOracleImpl;
import entities.Admin;
import entities.Staff;

public class AdminManager {
	private static final AdminDao dao = new AdminDaoOracleImpl();
	private static final StaffDao sdao = new StaffDaoOracleImpl();
	
	public Admin register(Admin a) throws Exception {		
		//1.先检查是否已被注册
		List<Admin> list = dao.getByUsername(a.getUsername());
		if(list.size()>0) {
			throw new Exception("用户名已被抢注，请重新输入");
		}
		//2.保存		
		Admin aa = dao.add(a);
		if(aa==null) {
			throw new Exception("注册失败，数据库异常");
		}
		return aa;
	}
	
	public Admin login(Admin a) {
		List<Admin> list = dao.getByUsername(a.getUsername());
		if(list.size()>0) {
			if(list.get(0).getPassword().equals(a.getPassword())) {
				return list.get(0);
			}
		}
		return null;
	}
	
	public void addstaff(Staff s) {    
		sdao.add(s);	
	}

	public void updstaff(int id, String password, int tel, String dept, String job) {
		Staff s = sdao.getById(id);
		s.setPassword(password);
		s.setTel(tel);
		s.setDept(dept);
		s.setJob(job);
		sdao.upd(s);
	}
	public Staff modpassword1(int id, String password) {
		Staff a=sdao.getById(id);
		a.setPassword(password);
		sdao.upd(a);
		return a;
	}
	
	public List<Staff> mystaff(Admin admin) {
		String where = " adminid = " + admin.getId();
		return sdao.getByWhere(where);
	}
	
	public boolean delstaff(int id) {
		Staff staff = sdao.getById(id);
		return sdao.del(staff);
	}

	public List<Staff> allstaff(Staff staff, int adminid) {
		Staff a =sdao.getById(adminid);
		String where = " adminid = " + a.getAdminid();
		return sdao.getByWhere(where);
	}

	public Staff stafflogin(Staff a) {
		List<Staff> list = sdao.getByUsername(a.getUsername());
		if(list.size()>0) {
			if(list.get(0).getPassword().equals(a.getPassword())) {
				return list.get(0);
			}
		}
		return null;
	}

	public Staff modpassword(int id, String password) {
		Staff a = sdao.getById(id);
		a.setPassword(password);
		sdao.upd(a);
		return a;
	}
	
	public Staff modtel(int id, int tel) {
		Staff a = sdao.getById(id);
		a.setTel(tel);
		sdao.upd(a);
		return a;
	}

	public Staff moddept(int id, String dept) {
		Staff a = sdao.getById(id);
		a.setDept(dept);
		sdao.upd(a);
		return a;
	}

	public Staff modjob(int id, String job) {
		Staff a = sdao.getById(id);
		a.setJob(job);
		sdao.upd(a);
		return a;
	}

	public List<Staff> staffs(int id) {
		String where = " id = " + id;
		return sdao.getByid(where);
	}

	public void sign(int id){
		Staff f = sdao.getByid1(id);
		if(f == null){
			System.out.println("无此员工！");
		}else{
			 System.out.println("签到成功");
		}
	}

	
	
}
