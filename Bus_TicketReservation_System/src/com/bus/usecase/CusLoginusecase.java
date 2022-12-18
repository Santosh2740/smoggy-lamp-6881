package com.bus.usecase;

import java.util.InputMismatchException;

import java.util.Scanner;

import com.bus.bean.Customer;
import com.bus.color.ConsoleColor;
import com.bus.dao.CustomerDao;
import com.bus.dao.CustomerDaoImpl;
import com.bus.exceptions.CustomerException;

public class CusLoginusecase {

	public static Customer CusLogin() {
		
		Customer customer = null;
		
		try {
			
			Scanner sc = new Scanner (System.in);
			
			System.out.print(ConsoleColor.ORANGE + "Enter Username : " + ConsoleColor.RESET);
			String username = sc.next();
			
			System.out.print(ConsoleColor.ORANGE + "Enter Password : " + ConsoleColor.RESET);
			String password = sc.next();
			
			CustomerDao dao = new CustomerDaoImpl();
			
			try {
				customer = dao.cusLogin(username, password);
				System.out.println();
				System.out.println(ConsoleColor.PURPLE_BOLD_BRIGHT + "Welcome " + customer.getFirstName() + " " + customer.getLastName() + ConsoleColor.RESET);
				System.out.println();
			} catch (CustomerException e) {
				
				System.out.println(ConsoleColor.RED_BACKGROUND + e.getMessage() + ConsoleColor.RESET);
			}
		}
		catch (InputMismatchException e) {
			System.out.println(ConsoleColor.RED_BACKGROUND + e.getMessage() + ConsoleColor.RESET);
		}
		
		return customer;

	}

}
