package com.bus.usecase;

import java.util.Scanner;


import com.bus.bean.Customer;
import com.bus.color.ConsoleColor;
import com.bus.dao.CustomerDao;
import com.bus.dao.CustomerDaoImpl;
import com.bus.exceptions.BusException;

public class CancelTicketbusNameusecase {
	
	public  static void cancelTicket(Customer customer) {

		Scanner sc = new Scanner(System.in);
		
		System.out.print(ConsoleColor.ORANGE + "Enter Bus Name : " + ConsoleColor.RESET);
		String bName = sc.nextLine();
		
		CustomerDao dao = new CustomerDaoImpl();
		try {
			
			int customerId = customer.getCustomerId();
			String message = dao.cancelTicket(bName, customerId);
			
			if (message.equals("Ticket cancelled Successfully")) {
				System.out.println(ConsoleColor.GREEN_BACKGROUND + message + ConsoleColor.RESET);
			}
			else {
				System.out.println(ConsoleColor.RED_BACKGROUND + message + ConsoleColor.RESET);
			}
			
		} catch (BusException e) {
			System.out.println(ConsoleColor.RED_BACKGROUND + e.getMessage() + ConsoleColor.RESET);
		}
	
	}
}
