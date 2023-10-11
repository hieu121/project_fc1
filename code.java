package project;

import java.util.Scanner;
import java.io.*;

interface count_tax {
	abstract int c_tax();
}

abstract class staff implements count_tax {
	int id;
	int tax;
	String name;
	int basic_salary;

	staff(int id, int tax, String name, int basic_salary) {
		this.id = id;
		this.tax = tax;
		this.name = name;
		this.basic_salary = basic_salary;
	}

	staff() {
		this.id = 0;
		this.tax = 0;
		this.name = "";
		this.basic_salary = 0;
	}

	void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("id:");
		id = sc.nextInt();
		sc.nextLine();
		System.out.println("name:");
		name = sc.nextLine();
		System.out.println("basic_salary");
		basic_salary = sc.nextInt();

	}

	void output() {
		System.out.println("id:" + id + " name:" + name + " basic_salary:" + basic_salary);
	}

	int salary() {
		return 0;
	}

	int display() {
		return 0;
	}
}

class E_sale extends staff {
	int commissions;

	E_sale() {
		this.commissions = 0;
	}

	E_sale(int commissions) {
		this.commissions = commissions;
	}

	@Override
	public int c_tax() {
		return tax = (int) ((int) salary() * 0.1);
	}

	void input() {
		super.input();
		Scanner sc = new Scanner(System.in);
		System.out.println("commissions:");
		commissions = sc.nextInt();
	}

	void output() {
		super.output();
		System.out.println("commissions:" + commissions + " salary:" + display());
	}

	int salary() {
		return basic_salary + commissions;
	}

	int display() {
		return salary() - c_tax();
	}

}

class E_chef extends staff {
	int ot;

	E_chef(int ot) {
		this.ot = ot;
	}

	E_chef() {
		this.ot = 0;
	}

	@Override
	public int c_tax() {
		return tax = (int) ((int) salary() * 0.1);
	}

	void input() {
		super.input();
		Scanner sc = new Scanner(System.in);
		System.out.println("ot:");
		ot = sc.nextInt();
	}

	void output() {
		super.output();
		System.out.println("ot:" + ot + " salary:" + display());
	}

	int salary() {
		return basic_salary + ot;
	}

	int display() {
		return salary() - c_tax();
	}

}

class E_serve extends staff {
	int tips;

	E_serve() {
		this.tips = 0;
	}

	E_serve(int tips) {
		this.tips = tips;
	}

	@Override
	public int c_tax() {
		return tax = (int) ((int) salary() * 0.1);
	}

	void input() {
		super.input();
		Scanner sc = new Scanner(System.in);
		System.out.println("tips:");
		tips = sc.nextInt();
	}

	void output() {
		super.output();
		System.out.println("tips:" + tips + " salary:" + display());
	}

	int salary() {
		return basic_salary + tips;
	}

	int display() {
		return salary() - c_tax();
	}

}

class list_staff {
	int n;
	staff[] list = new staff[100];

	list_staff() {
		this.n = n;
		staff[] list;
	}

	void input() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("nhap so luong staff:");
			n = sc.nextInt();
		} while (n <= 0 || n > 100);
		int loai;
		for (int i = 0; i < n; i++) {
			do {
				System.out.println("nhap loai staff(1.sale/2.chef/3.serve):");
				loai = sc.nextInt();
			} while (loai < 1 || loai > 3);
			if (loai == 1) {
				E_sale sale = new E_sale();
				sale.input();
				list[i] = sale;
			} else if (loai == 2) {
				E_chef chef = new E_chef();
				chef.input();
				list[i] = chef;
			} else if (loai == 3) {
				E_serve serve = new E_serve();
				serve.input();
				list[i] = serve;
			} else {
				System.out.println("ban nhap sai!!!");
				break;
			}
		}
	}

	void output() {
		for (int i = 0; i < n; i++) {
			list[i].output();
		}
	}

	void sort() {
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (list[j].display() < list[j + 1].display()) {
					staff temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
	}

	void good_staff() {
		list[0].output();
	}

	void search(int look) {
		for (int i = 0; i < n; i++) {
			if (list[i].id == look) {
				list[i].output();
				break;
			}
		}
	}

	void delete(int d) {
		for (int i = 0; i < n; i++) {
			if (list[i].id == d) {
				for (int j = i; j < n - 1; j++) {
					list[j] = list[j + 1];
				}
				n--;
				System.out.println("da xoa nhan vien co id la: " + d);
			}
		}
	}

	void sortStaffFile() throws IOException {
		File f = new File("D:\\fc1\\project\\test.txt");
		FileWriter fw = new FileWriter(f);
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (list[j].display() > list[j + 1].display()) { 
					staff temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
		try {
			fw.write("===danh sach sinh vien sap xep theo luong tu thap den cao===\n");
			for (int i = 0; i < n; i++) {
				fw.write(list[i].id + " " + list[i].name + " " + list[i].display() + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		fw.close();
		System.out.println("ghi file thanh cong!!!" );
	}
	void sumSalaryToFile() throws IOException {
		File f = new File("D:\\fc1\\project\\sum.txt");
		FileWriter fw = new FileWriter(f,true);
		float sum=0;
		for(int i=0;i<n;i++) {
			sum=sum+list[i].display();		}
		try {
				fw.write("sum of salary:"+sum + "\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		fw.close();
		System.out.println("ghi file thanh cong!!!" );
	}
}

public class code {

	public static void main(String[] args) throws IOException {
		list_staff list = new list_staff();
		Scanner sc = new Scanner(System.in);
		list.input();
		int funC;
		System.out.println("=========MENU=========");
		System.out.println("1.Xuat mang");
		System.out.println("2.Sap xep so luong tu cao den thap");
		System.out.println("3.In nhan vien co luong cao nhat");
		System.out.println("4.Tim kiem nhan vien qua id");
		System.out.println("5.Xoa nhan vien qua id");
		System.out.println("6.Sap xep nhan vien theo luong thap den cao va luu vao file");
		System.out.println("7.Tinh tong tat ca luong cua nhan vien");
		do {
			System.out.println("NHAP CHUC NANG:");
			funC = sc.nextInt();
		} while (funC < 1 || funC > 7);
		if (funC == 1) {
			System.out.println("Xuat mang");
			list.output();
		} else if (funC == 2) {
			System.out.println("mang sau khi sap xep tu lon den be theo luong:");
			list.sort();
			list.output();
		} else if (funC == 3) {
			list.sort();
			System.out.println("nhan vien xuat sac co luong cao nhat la:");
			list.good_staff();
		} else if (funC == 4) {
			int look;
			System.out.println("nhap id nhan vien muon tim:");
			look = sc.nextInt();
			list.search(look);
		} else if (funC == 5) {
			int d;
			System.out.println("nhap id nhan vien muon xoa:");
			d = sc.nextInt();
			list.delete(d);
			System.out.println("mang sau khi xoa:");
			list.output();
		} else if (funC == 6) {
			list.sortStaffFile();
		} else if (funC == 7) {
			list.sumSalaryToFile();
		} else {
			System.out.println("nhap sai!!!!");
		}

	}
//code by Hieu
}
