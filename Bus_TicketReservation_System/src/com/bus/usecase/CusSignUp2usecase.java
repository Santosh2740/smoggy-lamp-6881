package com.bus.usecase;

import java.util.InputMismatchException;

import java.util.Scanner;

import com.bus.bean.Customer;
import com.bus.color.ConsoleColor;
import com.bus.dao.CustomerDao;
import com.bus.dao.CustomerDaoImpl;

public class CusSignUp2usecase {

	public static boolean cusSignUp() {
		
		boolean flag = false;
		
		try {
			Scanner sc = new Scanner(System.in);
			
			System.out.print(ConsoleColor.ORANGE + "Enter Username : " + ConsoleColor.RESET);
			String username = sc.next();
			
			System.out.print(ConsoleColor.ORANGE + "Enter Password : " + ConsoleColor.RESET);
			String password = sc.next();
			
			System.out.print(ConsoleColor.ORANGE + "Enter First Name : " + ConsoleColor.RESET);
			String firstName = sc.next();
			
			System.out.print(ConsoleColor.ORANGE + "Enter Last Name : " + ConsoleColor.RESET);
			String lastName = sc.next();
			
			sc.nextLine();
			System.out.print(ConsoleColor.ORANGE + "Enter Address : " + ConsoleColor.RESET);
			String address = sc.nextLine();
			
			System.out.print(ConsoleColor.ORANGE + "Enter Mobile : " + ConsoleColor.RESET);
			String mobile = sc.next();
			
			CustomerDao dao = new CustomerDaoImpl();
			Customer customer = new Customer(0, username, password, firstName, lastName, address, mobile);
			
			String result = dao.cusSignUp(customer);
			
			
			if (result == "Sign up Successfull") {
				flag = true;
				System.out.println(ConsoleColor.GREEN_BACKGROUND + result + ConsoleColor.RESET);
			}
			else {
				System.out.println(ConsoleColor.RED_BACKGROUND + result + ConsoleColor.RESET);
			}
			
		}
		catch (InputMismatchException e) {
			System.out.println(ConsoleColor.RED_BACKGROUND + "Invalid Input" + ConsoleColor.RESET);
		}
		
		return flag;
	}

}
