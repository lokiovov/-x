package entities;

public class Staff {
	private int id;
	private String username;
	private String password;
	private String name;
	private int idcard;
	private int tel;
	private String dept;
	private String job;
	private int adminid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdcard() {
		return idcard;
	}
	public void setIdcard(int idcard) {
		this.idcard = idcard;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
	public Staff(int id, String username, String password, String name,
			int idcard, int tel, String dept, String job, int adminid) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.idcard = idcard;
		this.tel = tel;
		this.dept = dept;
		this.job = job;
		this.adminid = adminid;
	}
	public Staff() {
		super();
	}
	@Override
	public String toString() {
		return "[Ô±¹¤id=" + id + ", username=" + username + ", password="
				+ password + ", name=" + name + ", idcard=" + idcard + ", tel="
				+ tel + ", dept=" + dept + ", job=" + job + ", adminid="
				+ adminid + "]";
	}
	
}
