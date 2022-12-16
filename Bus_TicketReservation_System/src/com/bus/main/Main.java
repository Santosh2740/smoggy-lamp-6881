package com.bus.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.bus.bean.Customer;
import com.bus.color.ConsoleColor;
import com.bus.usecase.AddBusNo2;
import com.bus.usecase.AdminLoginusecase;
import com.bus.usecase.BookTicketbNameusecase;
import com.bus.usecase.CancelTicketbNameusecase;
import com.bus.usecase.CusLoginusecase;
import com.bus.usecase.CusSignUp2usecase;
import com.bus.usecase.UpdateStatususecase;
import com.bus.usecase.ViewAllTicketsusecase;
import com.bus.usecase.ViewTicketusecase;
public class Main {
	static void AdminOrCustomer() {
		System.out.println(ConsoleColor.BLUE_BRIGHT + "+---------------------------+" + "\n"
						 						   + "| 1. Login As Administrator |" + "\n"
						 						   + "| 2. Login As Customer      |" + "\n"
						 						   + "| 3. Exit                   |" + "\n"
						 						   + "+---------------------------+" + ConsoleColor.RESET);
		choice();
	}
	
	static void choice() {
		Scanner sc = new Scanner(System.in);
		
		int choice = 0;
		try {
			choice = sc.nextInt();
		}
		catch (InputMismatchException e) {
			System.out.println(ConsoleColor.RED_BACKGROUND + "Input type should be number" + ConsoleColor.RESET);
			AdminOrCustomer();
		}
		
		if (choice == 1) {
			System.out.println(ConsoleColor.GREEN_STRIKE + "Welcome Admin ! Please Login to your account" + ConsoleColor.RESET);
			AdminLogin();
		}
		else if (choice == 2) {
			System.out.println(ConsoleColor.GREEN_STRIKE + "Welcome Customer !" + ConsoleColor.RESET);
			customerLoginOrSignup();
		}
		else if (choice == 3) {
			System.out.println(ConsoleColor.GREEN_STRIKE + "Thank you ! Visit again" + ConsoleColor.RESET);
			System.exit(0);
		}
		else {
			System.out.println(ConsoleColor.GREEN_STRIKE + "Please choose a number from below options" + ConsoleColor.RESET);
			AdminOrCustomer();
		}
	}
	
	static void AdminLogin() {
		
		Boolean result = AdminLoginusecase.AdminLogin();

		if (result) adminMethods();
		else {
			AdminLogin();
		}
	}
	
	static void adminMethods() {
		System.out.println(ConsoleColor.BLUE_BRIGHT + "+--------------------------------+" + "\n"
						 + "| Welcome Admin                  |" + "\n"
						 + "| 1. Add Bus                     |" + "\n"
						 + "| 2. Confirm Tickets of Customer |" + "\n"
						 + "| 3. View All Bookings           |" + "\n"
						 + "| 4. Back                        |" + "\n"
						 + "| 5. Exit                        |" + "\n"
						 + "+--------------------------------+" + ConsoleColor.RESET);
		
		Scanner sc = new Scanner(System.in);
		
		int choice = 0;
		try {
			choice = sc.nextInt();
			if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5) {
				System.out.println(ConsoleColor.RED_BACKGROUND + "Please choose a number from below options" + ConsoleColor.RESET);
				adminMethods();
			}
			else adminChoice(choice);
		}
		catch (InputMismatchException e) {
			
			System.out.println(ConsoleColor.RED_BACKGROUND + "Input type should be number" + ConsoleColor.RESET);
			adminMethods();
		}
	}
	
	static void adminChoice(int choice) {
		
			switch(choice) {
				case 1 : {
					AddBusNo2.AddBus();
					adminMethods();
				}
				break;
				case 2 : {
					UpdateStatususecase.updateStatus();
					adminMethods();
				}
				break;
				case 3 : {
					ViewAllTicketsusecase.viewAllTicket();
					adminMethods();
				}
				break;
				case 4 : AdminOrCustomer();
				break;	
				case 5 : {
					System.out.println(ConsoleColor.GREEN_STRIKE+ "Thank you ! Visit again" + ConsoleColor.RESET);
					System.exit(0);
				}
			}
	}
	
	static void customerLoginOrSignup() {
		System.out.println(ConsoleColor.BLUE_BRIGHT + "+--------------------------------+" + "\n"
				                                + "| 1. Login to your Account       |" + "\n"
				                                + "| 2. Don't have Account? Sign Up |" + "\n"
				                                + "| 3. Back                        |" + "\n"
				                                + "| 4. Exit                        |" + "\n"
				                                + "+--------------------------------+" + ConsoleColor.RESET);
		try {
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			
			if (choice == 1) {
				customerLogin();
			}
			else if (choice == 2) {
				customerSignup();
			}
			else if (choice == 3) {
				AdminOrCustomer();
			}
			else if (choice == 4) {
				System.out.println(ConsoleColor.GREEN_STRIKE + "Thank you ! Visit again" + ConsoleColor.RESET);
				System.exit(0);
			}
			else {
				System.out.println(ConsoleColor.RED_BACKGROUND + "Please choose a number from below options" + ConsoleColor.RESET);
				customerLoginOrSignup();
			}
		}
		catch (InputMismatchException e) {
			System.out.println(ConsoleColor.RED_BACKGROUND + "Input type should be number" + ConsoleColor.RESET);
			customerLoginOrSignup();
		}
		
	}
	
	static void customerLogin() {
		Customer customer = CusLoginusecase.CusLogin();
		
		if (customer == null) {
			customerLogin();
		}
		else {
			System.out.println(ConsoleColor.GREEN_BACKGROUND + "Login Successfull" + ConsoleColor.RESET);
			customerMethods(customer);
		}
		
	}
	
	static void customerSignup() {
		boolean flag = CusSignUp2usecase.cusSignUp();
		
		if (flag) {
			System.out.println(ConsoleColor.GREEN_STRIKE + "Login to your Account" + ConsoleColor.RESET);
			customerLogin();
		}
		else {
			customerSignup();
		}
	}
	
	static void customerMethods(Customer customer) {
		System.out.println(ConsoleColor.BLUE_BRIGHT + "+--------------------------------+" + "\n"
				 		 + "| 1. Book Bus Ticket             |" + "\n"
				         + "| 2. Cancel Bus Ticket           |" + "\n"
				         + "| 3. View Status of your Tickets |" + "\n"
				         + "| 4. Back                        |" + "\n"
				         + "| 5. Exit                        |" + "\n"
				         + "+--------------------------------+" + ConsoleColor.RESET);
		
		Scanner sc = new Scanner(System.in);
		
		int choice = 0;
		try {
			choice = sc.nextInt();
			if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5) {
				System.out.println(ConsoleColor.RED_BACKGROUND + "Please choose a number from below options" + ConsoleColor.RESET);
				customerMethods(customer);
			}
			else customerChoice(choice, customer);
		}
		catch (InputMismatchException e) {
			
			System.out.println(ConsoleColor.RED_BACKGROUND + "Input type should be number" + ConsoleColor.RESET);
			customerMethods(customer);
		}
	}
	
	static void customerChoice(int choice, Customer customer) {
		switch(choice) {
		case 1 : {
			BookTicketbNameusecase.BookTicketbName(customer);
			customerMethods(customer);
		}
		break;
		case 2 : {
			CancelTicketbNameusecase.cancelTicket(customer);
			customerMethods(customer);
		}
		break;
		case 3 : {
			ViewTicketusecase.viewTicket(customer);
			customerMethods(customer);
		}
		break;
		case 4 : {
			customerLoginOrSignup();
		}
		case 5 : {
			System.out.println(ConsoleColor.GREEN_STRIKE + "Thank you ! Visit again" + ConsoleColor.RESET);
			System.exit(0);
		}
	}
	}
	

	public static void main(String[] args) {
		
		AdminOrCustomer();
				
	}	


}
