package com.bus.usecase;

import java.util.Scanner;


import com.bus.color.ConsoleColor;
import com.bus.dao.AdminDao;
import com.bus.dao.AdminDaoImpl;

public class AdminLoginusecase {

	public static boolean AdminLogin() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print(ConsoleColor.ORANGE +  "Enter username : " + ConsoleColor.RESET);
		String username = sc.next();
		
		System.out.print(ConsoleColor.ORANGE + "Enter password : " + ConsoleColor.RESET);
		String password = sc.next();
		
		AdminDao dao = new AdminDaoImpl();
		String result =  dao.adminLogin(username, password);
		
		if (result.equals("Login Successfull")){
			System.out.println();
			System.out.println(ConsoleColor.GREEN_BACKGROUND + result + ConsoleColor.RESET);
			return true;
		}
		else {
			System.out.println();
			System.out.println(ConsoleColor.RED_BACKGROUND + result + ConsoleColor.RESET);
			return false;
		}

	}

}
