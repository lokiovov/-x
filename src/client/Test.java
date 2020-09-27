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
	//����һ��ȫ�ֱ���������ʵ�ֹ���Աͨ��Ա��id��ѯ
	static int id1;

	//ע��
	public static void register() {
		System.out.println("��ӭע��");
		System.out.print("�����û�����");
		String username = input.next();
		System.out.print("�������룺");
		String password = input.next();		
		System.out.print("ȷ�����룺");
		String password2 = input.next();
		if(!password.equals(password2)) {
			System.out.println("����ȷ�ϴ���");
			return;
		}		
		Admin a = new Admin(0, username, password);		
		try{
			a=aa.register(a);
			System.out.println("ע��ɹ�");
			System.out.println("��Ĺ���Աid��"+a.getId());
		}catch(Exception e){
			System.out.println("ע��ʧ�ܣ�ʧ��ԭ����"+e.getMessage());
		}
	}
	
	//��¼
	private static void login() {
		System.out.println("��ӭ��¼");
		System.out.print("�����û�����");
		String username = input.next();
		System.out.print("�������룺");
		String password = input.next();		
		Admin a =new Admin(0, username, password);
		a = aa.login(a);
		if(a == null) {
			System.out.println("�û����������");
		}else {
			System.out.println("��¼�ɹ�");
			admin=a;
			System.out.println(admin);
		}
	}
	
	//��ְ
	private static void addstaff() {
		if(admin == null) {
			System.out.println("���ȵ�¼������ְ");
			return;
		}
			System.out.print("����Ա���û�����");
			String username = input.next();
			System.out.print("����Ա�����룺");
			String password = input.next();	
			System.out.print("����Ա��������");
			String name = input.next();
			System.out.print("����Ա�����֤�ţ�");
			int idcard = input.nextInt();
			System.out.print("����Ա���绰��");
			int tel = input.nextInt();
			System.out.print("����Ա�����ţ�");
			String dept = input.next();
			System.out.print("����Ա��ְλ��");
			String job = input.next();
			Staff s = new Staff(0,username, password, name, idcard, tel, dept, job, admin.getId());
			try{			
				aa.addstaff(s);
				System.out.println("¼��ɹ�");
				System.out.println("Ա��idΪ��"+s.getId());
				List<Staff> staffs = aa.mystaff(admin);
				for (Staff staff : staffs) {
					System.out.println(staff);
				  }	
			}catch(Exception e){
				System.out.println("���ʧ�ܣ�ʧ��ԭ����"+e.getMessage());
			}		
		}
	
	//����Ա�޸���Ϣ
	private static void updstaff() {
		if(admin== null) {
			System.out.println("���ȵ�¼�����޸�");
			return;
		}
		allstaff();
		System.out.println("Ա��id��");
		int id=input.nextInt();
			id1 = id;
		
		System.out.println("��ѡ���޸ĵ���Ϣ��1.�޸����� 2.�޸ĵ绰3.�޸Ĳ��� 4.�޸�ְλ  ");
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
	//����
	private static void modpassword1() {
		System.out.print("���������룺");
		String password = input.next();
		System.out.print("����ȷ�ϣ�");
		String password2 = input.next();		
		if(!password.equals(password2)) {
			System.out.println("����ȷ�ϴ���");
			return;
		}
		Staff a = aa.modpassword1(id1, password);
		if(a == null) {
			System.out.println("�������ʧ��");
		}else {
			System.out.println("������³ɹ�");
			staff = a;
		}
	}
	//�绰
	private static void modtel() {
			System.out.print("�����µ绰���룺");
			int tel = input.nextInt();
			Staff a = aa.modtel(id1, tel);
			if(a == null) {
				System.out.println("�绰����ʧ��");
			}else {
				System.out.println("�绰���³ɹ�");
				staff = a;
				List<Staff> l = aa.staffs(id1);
				 for(Staff l1 : l){
					 System.out.println(l1);
				 }
			   }
			}
	//����
	private static void moddept() {
			System.out.print("�����²��ţ�");
			String dept = input.next();
			
			Staff a = aa.moddept(id1, dept);
			if(a == null) {
				System.out.println("���Ÿ���ʧ��");
			}else {
				System.out.println("���Ÿ��³ɹ�");
				staff = a;
				List<Staff> l = aa.staffs(id1);
				 for(Staff l1 : l){
					 System.out.println(l1);
				 }
			}
		}
	//ְλ
	private static void modjob() {
			System.out.print("������ְλ��");
			String job = input.next();
			
			Staff a = aa.modjob(id1, job);
			if(a == null) {
				System.out.println("ְλ����ʧ��");
			}else {
				System.out.println("ְλ���³ɹ�");
				staff = a;
				List<Staff> l = aa.staffs(id1);
				 for(Staff l1 : l){
					 System.out.println(l1);
				 }
			}
		}
		
	//Ա����¼
	private static void stafflogin() {
		if(admin == null) {
			System.out.println("���ȵ�¼,�ڲ�ѯ");
			return;
		}
		System.out.println("��ӭԱ����¼");
		System.out.print("�����û�����");
		String username = input.next();
		System.out.print("�������룺");
		String password = input.next();		
		Staff a =new Staff(0,username, password,"", 0, 0, "", "", admin.getId());
		a = aa.stafflogin(a);
		if(a == null) {
			System.out.println("�û����������");
		}else {
			System.out.println("��¼�ɹ�");
			staff=a;
			System.out.println(staff);
		}
	}
	
	//ɾ��
	private static void delstaff() {
		if(admin == null) {
			System.out.println("���ȵ�¼���ڽ�����ְ");
			return;
		}
		allstaff();
	   System.out.println("��������ְԱ��id��");
	   int id=input.nextInt();	   
	   System.out.println("�Ƿ�ɾ��Ա��(y/n)");
		String answer = input.next();
		if(answer.toLowerCase().equals("y")) {
			boolean b = aa.delstaff(id);
			if(b) {
				System.out.println("ɾ���ɹ�");
			}else {
				System.out.println("ɾ��ʧ��");
			}
		}else {
			System.out.println("����ȡ��");
		}
	}
	
	//��ʾԱ����Ϣ
	private static void allstaff() {
			if(admin == null) {
				System.out.println("���ȵ�¼,�ڲ�ѯ");
				return;
			}
			List<Staff> staffs = aa.mystaff(admin);
			for (Staff staff : staffs) {
				System.out.println(staff);
			}
		}
	
	//Ա���޸�����
	private static void modstaff() {
		stafflogin();
		System.out.print("���������룺");
		String password = input.next();
		System.out.print("����ȷ�ϣ�");
		String password2 = input.next();		
		if(!password.equals(password2)) {
			System.out.println("����ȷ�ϴ���");
			return;
		}
		Staff a = aa.modpassword(staff.getId(), password);
		if(a == null) {
			System.out.println("�������ʧ��");
		}else {
			System.out.println("������³ɹ�");
			staff = a;
		}
	}
	
	//ǩ��
	private static void staffsign() {
		System.out.println("������Ա��id��");
		int id=input.nextInt();
		 aa.sign(id);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
	    }
	
	
	
	public static void main(String[] args) {
		while(true){
			System.out.println("��ӭʹ��Ա������ϵͳ");
			System.out.println("��ѡ��1.ע�� 2.��¼ 3.��ְ 4.�޸�Ա����Ϣ  5.��ְ "
					+ "6.�ҵ�Ա��  7.Ա���޸����� 8.Ա��ǩ��");
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

	
	

	
	

	

	

	

	

	

	
	

