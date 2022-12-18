package com.bus.usecase;

import java.util.InputMismatchException;

import java.util.Scanner;

import com.bus.bean.Customer;
import com.bus.color.ConsoleColor;
import com.bus.dao.CustomerDao;
import com.bus.dao.CustomerDaoImpl;
import com.bus.exceptions.BusException;

public class BookTicketbusNameusecase {

	public static void BookTicketbName(Customer customer) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print(ConsoleColor.ORANGE + "Enter Bus Name : " + ConsoleColor.RESET);
		String busName = sc.next();
		
		CustomerDao dao = new CustomerDaoImpl();
		try {
			System.out.print(ConsoleColor.ORANGE + "Enter no. of Tickets to Book : " + ConsoleColor.RESET);
			int no = sc.nextInt();
			
			int customerId = customer.getCustomerId();
			String message = dao.bookTicket(busName, customerId, no);
			
			if (message.equals("Ticket Booked Successfully")) {
				System.out.println(ConsoleColor.GREEN_BACKGROUND + message + ConsoleColor.RESET);
			}
			else {
				System.out.println(ConsoleColor.RED_BACKGROUND + message + ConsoleColor.RESET);
			}
			
		} catch (BusException e) {
			System.out.println(ConsoleColor.RED_BACKGROUND + e.getMessage() + ConsoleColor.RESET);
		}
		catch (InputMismatchException e) {
			System.out.println(ConsoleColor.RED_BACKGROUND + "Invalid input" + ConsoleColor.RESET);
		}
		
	}
}
