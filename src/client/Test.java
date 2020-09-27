package client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.Impl.StaffDaoOracleImpl;
import manager.AdminManager;
import entities.Admin;
import entities.Staff;

public class Test {
	private static final Scanner input = new Scanner(System.in);
	private static final AdminManager aa= new AdminManager();
	private static Admin admin = null;
	private static Staff staff = null;
	//定义一个全局变量，用来实现管理员通过员工id查询
	static int id1;

	//注册
	public static void register() {
		System.out.println("欢迎注册");
		System.out.print("输入用户名：");
		String username = input.next();
		System.out.print("输入密码：");
		String password = input.next();		
		System.out.print("确认密码：");
		String password2 = input.next();
		if(!password.equals(password2)) {
			System.out.println("密码确认错误");
			return;
		}		
		Admin a = new Admin(0, username, password);		
		try{
			a=aa.register(a);
			System.out.println("注册成功");
			System.out.println("你的管理员id："+a.getId());
		}catch(Exception e){
			System.out.println("注册失败，失败原因是"+e.getMessage());
		}
	}
	
	//登录
	private static void login() {
		System.out.println("欢迎登录");
		System.out.print("输入用户名：");
		String username = input.next();
		System.out.print("输入密码：");
		String password = input.next();		
		Admin a =new Admin(0, username, password);
		a = aa.login(a);
		if(a == null) {
			System.out.println("用户名密码错误");
		}else {
			System.out.println("登录成功");
			admin=a;
			System.out.println(admin);
		}
	}
	
	//入职
	private static void addstaff() {
		if(admin == null) {
			System.out.println("请先登录，在入职");
			return;
		}
			System.out.print("输入员工用户名：");
			String username = input.next();
			System.out.print("输入员工密码：");
			String password = input.next();	
			System.out.print("输入员工姓名：");
			String name = input.next();
			System.out.print("输入员工身份证号：");
			int idcard = input.nextInt();
			System.out.print("输入员工电话：");
			int tel = input.nextInt();
			System.out.print("输入员工部门：");
			String dept = input.next();
			System.out.print("输入员工职位：");
			String job = input.next();
			Staff s = new Staff(0,username, password, name, idcard, tel, dept, job, admin.getId());
			try{			
				aa.addstaff(s);
				System.out.println("录入成功");
				System.out.println("员工id为："+s.getId());
				List<Staff> staffs = aa.mystaff(admin);
				for (Staff staff : staffs) {
					System.out.println(staff);
				  }	
			}catch(Exception e){
				System.out.println("添加失败，失败原因是"+e.getMessage());
			}		
		}
	
	//管理员修改信息
	private static void updstaff() {
		if(admin== null) {
			System.out.println("请先登录，在修改");
			return;
		}
		allstaff();
		System.out.println("员工id：");
		int id=input.nextInt();
			id1 = id;
		
		System.out.println("请选择修改的信息：1.修改密码 2.修改电话3.修改部门 4.修改职位  ");
		int choice2 = input.nextInt();
		switch(choice2){
		case 1:
			modpassword1();
			break;
		case 2:
			modtel();
			break;
		case 3:
			moddept();
			break;
		case 4:
			modjob();
			break;
		}
	}
	//密码
	private static void modpassword1() {
		System.out.print("输入新密码：");
		String password = input.next();
		System.out.print("密码确认：");
		String password2 = input.next();		
		if(!password.equals(password2)) {
			System.out.println("密码确认错误");
			return;
		}
		Staff a = aa.modpassword1(id1, password);
		if(a == null) {
			System.out.println("密码更新失败");
		}else {
			System.out.println("密码更新成功");
			staff = a;
		}
	}
	//电话
	private static void modtel() {
			System.out.print("输入新电话号码：");
			int tel = input.nextInt();
			Staff a = aa.modtel(id1, tel);
			if(a == null) {
				System.out.println("电话更新失败");
			}else {
				System.out.println("电话更新成功");
				staff = a;
				List<Staff> l = aa.staffs(id1);
				 for(Staff l1 : l){
					 System.out.println(l1);
				 }
			   }
			}
	//部门
	private static void moddept() {
			System.out.print("输入新部门：");
			String dept = input.next();
			
			Staff a = aa.moddept(id1, dept);
			if(a == null) {
				System.out.println("部门更新失败");
			}else {
				System.out.println("部门更新成功");
				staff = a;
				List<Staff> l = aa.staffs(id1);
				 for(Staff l1 : l){
					 System.out.println(l1);
				 }
			}
		}
	//职位
	private static void modjob() {
			System.out.print("输入新职位：");
			String job = input.next();
			
			Staff a = aa.modjob(id1, job);
			if(a == null) {
				System.out.println("职位更新失败");
			}else {
				System.out.println("职位更新成功");
				staff = a;
				List<Staff> l = aa.staffs(id1);
				 for(Staff l1 : l){
					 System.out.println(l1);
				 }
			}
		}
		
	//员工登录
	private static void stafflogin() {
		if(admin == null) {
			System.out.println("请先登录,在查询");
			return;
		}
		System.out.println("欢迎员工登录");
		System.out.print("输入用户名：");
		String username = input.next();
		System.out.print("输入密码：");
		String password = input.next();		
		Staff a =new Staff(0,username, password,"", 0, 0, "", "", admin.getId());
		a = aa.stafflogin(a);
		if(a == null) {
			System.out.println("用户名密码错误");
		}else {
			System.out.println("登录成功");
			staff=a;
			System.out.println(staff);
		}
	}
	
	//删除
	private static void delstaff() {
		if(admin == null) {
			System.out.println("请先登录，在进行离职");
			return;
		}
		allstaff();
	   System.out.println("请输入离职员工id：");
	   int id=input.nextInt();	   
	   System.out.println("是否删除员工(y/n)");
		String answer = input.next();
		if(answer.toLowerCase().equals("y")) {
			boolean b = aa.delstaff(id);
			if(b) {
				System.out.println("删除成功");
			}else {
				System.out.println("删除失败");
			}
		}else {
			System.out.println("操作取消");
		}
	}
	
	//显示员工信息
	private static void allstaff() {
			if(admin == null) {
				System.out.println("请先登录,在查询");
				return;
			}
			List<Staff> staffs = aa.mystaff(admin);
			for (Staff staff : staffs) {
				System.out.println(staff);
			}
		}
	
	//员工修改密码
	private static void modstaff() {
		stafflogin();
		System.out.print("输入新密码：");
		String password = input.next();
		System.out.print("密码确认：");
		String password2 = input.next();		
		if(!password.equals(password2)) {
			System.out.println("密码确认错误");
			return;
		}
		Staff a = aa.modpassword(staff.getId(), password);
		if(a == null) {
			System.out.println("密码更新失败");
		}else {
			System.out.println("密码更新成功");
			staff = a;
		}
	}
	
	//签到
	private static void staffsign() {
		System.out.println("请输入员工id：");
		int id=input.nextInt();
		 aa.sign(id);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	    }
	
	
	
	public static void main(String[] args) {
		while(true){
			System.out.println("欢迎使用员工管理系统");
			System.out.println("请选择：1.注册 2.登录 3.入职 4.修改员工信息  5.离职 "
					+ "6.我的员工  7.员工修改密码 8.员工签到");
			int choice = input.nextInt();
			switch(choice){
			case 1:
				register();
				break;
			case 2:
				login();
				break;
			case 3:
				addstaff();
				break;
			case 4:
				updstaff();
				break;
			case 5:
				delstaff();
				break;
			case 6:
				allstaff();
				break;
			case 7:
				modstaff();
				break;
			case 8:
			    staffsign();
			    break;
			
			}
		}
	}
}

	
	

	
	

	

	

	

	

	

	
	

