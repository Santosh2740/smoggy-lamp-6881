package com.bus.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.bus.bean.Customer;
import com.bus.color.ConsoleColor;
import com.bus.usecase.AddBusNo2;
import com.bus.usecase.AdminLoginusecase;
import com.bus.usecase.BookTicketbusNameusecase;
import com.bus.usecase.CancelTicketbusNameusecase;
import com.bus.usecase.CusLoginusecase;
import com.bus.usecase.CustomersSignUpNo2usecase;
import com.bus.usecase.UpdateStatususecase;
import com.bus.usecase.ViewAllTicketsusecase;
import com.bus.usecase.ViewTicketusecase;
public class Main {
	static void AdminOrCustomer() {
		System.out.println();
		System.out.println(ConsoleColor.BLUE_BRIGHT  + "+---------------------------+" + "\n"
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
			System.out.println();
			System.out.println(ConsoleColor.YELLOW_UNDERLINED + "Welcome Admin ! Please Login to your account" + ConsoleColor.RESET);
			System.out.println();
			AdminLogin();
		}
		else if (choice == 2) {
			System.out.println(ConsoleColor.YELLOW_UNDERLINED + "Welcome Customer !" + ConsoleColor.RESET);
			System.out.println();
			customerLoginOrSignup();
		}
		else if (choice == 3) {
			System.out.println(ConsoleColor.YELLOW_UNDERLINED + "Thank you ! Visit again" + ConsoleColor.RESET);
			System.out.println();
			System.exit(0);
		}
		else {
			System.out.println(ConsoleColor.YELLOW_UNDERLINED + "Please choose a number from below options" + ConsoleColor.RESET);
			System.out.println();
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
		System.out.println();
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
					System.out.println(ConsoleColor.YELLOW_UNDERLINED+ "Thank you ! Visit again" + ConsoleColor.RESET);
					System.exit(0);
				}
			}
	}
	
	static void customerLoginOrSignup() {
		System.out.println();
		System.out.println(ConsoleColor.ORANGE+ "+--------------------------------+" + "\n"
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
				System.out.println(ConsoleColor.YELLOW_UNDERLINED + "Thank you ! Visit again" + ConsoleColor.RESET);
				System.out.println();
				System.exit(0);
			}
			else {
				System.out.println(ConsoleColor.RED_BACKGROUND + "Please choose a number from below options" + ConsoleColor.RESET);
				System.out.println();
				customerLoginOrSignup();
			}
		}
		catch (InputMismatchException e) {
			System.out.println(ConsoleColor.RED_BACKGROUND + "Input type should be number" + ConsoleColor.RESET);
			System.out.println();
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
			System.out.println();
			customerMethods(customer);
		}
		
	}
	
	static void customerSignup() {
		boolean flag = CustomersSignUpNo2usecase.cusSignUp();
		
		if (flag) {
			System.out.println();
			System.out.println(ConsoleColor.YELLOW_UNDERLINED + "Login to your Account" + ConsoleColor.RESET);
			System.out.println();
			customerLogin();
		}
		else {
			customerSignup();
		}
	}
	
	static void customerMethods(Customer customer) {
		System.out.println();
		System.out.println(ConsoleColor.ORANGE + "+--------------------------------+" + "\n"
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
				System.out.println();
				customerMethods(customer);
			}
			else customerChoice(choice, customer);
		}
		catch (InputMismatchException e) {
			
			System.out.println(ConsoleColor.RED_BACKGROUND + "Input type should be number" + ConsoleColor.RESET);
			System.out.println();
			customerMethods(customer);
		}
	}
	
	static void customerChoice(int choice, Customer customer) {
		switch(choice) {
		case 1 : {
			BookTicketbusNameusecase.BookTicketbName(customer);
			customerMethods(customer);
		}
		break;
		case 2 : {
			CancelTicketbusNameusecase.cancelTicket(customer);
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
			System.out.println(ConsoleColor.YELLOW_UNDERLINED + "Thank you ! Visit again" + ConsoleColor.RESET);
			System.out.println();
			System.exit(0);
		}
	}
	}
	

	public static void main(String[] args) {
		
		AdminOrCustomer();
				
	}	


}
