package com.bus.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bus.bean.Bus;
import com.bus.color.ConsoleColor;
import com.bus.utility.DBUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public String adminLogin(String username, String password) {
		
		String message = "Invalid username or password";
		
		if (username.equals(AdminDao.username) && password.equals(AdminDao.password)) {
			 message = "Login Successfull";
		}
		
		return message;
	}

	@Override
	public String addBus(int busNo, String busName, String routeFrom, String routeTo, String bType, String arrival,
			String departure, int totalSeats, int availSeats, int fare) {
		
		String message = "Bus not Added";
		
		try (Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("insert into bus values (?,?,?,?,?,?,?,?,?,?)");
			
			ps.setInt(1, busNo);
			ps.setString(2, busName);
			ps.setString(3, routeFrom);
			ps.setString(4, routeTo);
			ps.setString(5, bType);
			ps.setString(6, arrival);
			ps.setString(7, departure);
			ps.setInt(8,totalSeats);
			ps.setInt(9, availSeats);
			ps.setInt(10, fare);
			
			int x = ps.executeUpdate();
			
			if (x > 0) message = "Bus added Successfully";
			
		}
		catch (SQLException e) {
			message  = e.getMessage();
		}
		
		return message;
	}

	@Override
	public String addBus(Bus bus) {
		String message = "Bus not Added";
		
		try (Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("insert into bus values (?,?,?,?,?,?,?,?,?,?)");
			
			ps.setInt(1, bus.getBusNo());
			ps.setString(2, bus.getbName());
			ps.setString(3, bus.getRouteFrom());
			ps.setString(4, bus.getRouteTo());
			ps.setString(5, bus.getbType());
			ps.setString(6, bus.getArrival());
			ps.setString(7, bus.getDeparture());
			ps.setInt(8,bus.getTotalSeats());
			ps.setInt(9, bus.getAvailSeats());
			ps.setInt(10, bus.getFare());
			
			int x = ps.executeUpdate();
			
			if (x > 0)  message = "Bus added Successfully";
			
		}
		catch (SQLException e) {
			message  = e.getMessage();
		}
		
		return message;
	}

	@Override
	public String updateStatus(int customerId) {
		String message = "Status not update for customer Id : " + customerId;
		
		try(Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("update booking set status = true where customerId = ?");
			ps.setInt(1, customerId);
			
			int x = ps.executeUpdate();
			if (x > 0) message = "Status successfully updated for customer Id : " + customerId;
			
		}
		catch (SQLException e) {
			message = e.getMessage();
		}
		
		return message;
	}

	@Override
	public void viewAllTickets() {
		boolean flag = false;
		
		try(Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps1 = conn.prepareStatement("select * from booking");
			
			ResultSet rs1 = ps1.executeQuery();
			
			while (rs1.next()) {
				flag = true;
				
				System.out.println(ConsoleColor.BROWN  + "+---------------------------------+" + ConsoleColor.RESET);
				System.out.println(ConsoleColor.BROWN  +"|"+" "+"Bus Id : " + rs1.getInt("bId")+"                      "+"|" + ConsoleColor.RESET);
				System.out.println(ConsoleColor.BROWN + "|"+" "+"Bus No : " + rs1.getInt("busNo")+"                      "+"|"  + ConsoleColor.RESET);
				System.out.println(ConsoleColor.BROWN  + "|"+" "+"Total tickets : " + (rs1.getInt("seatTo") - rs1.getInt("seatFrom") + 1)+"              "+"|"  + ConsoleColor.RESET);
				if (rs1.getInt("status") == 1) System.out.println(ConsoleColor.BROWN  + "|"+" "+"Status : Booked"+"                 " +"|" + ConsoleColor.RESET);
				else System.out.println(ConsoleColor.BROWN + "|"+" "+"Status : Pending" +"|" + ConsoleColor.RESET);
				
				System.out.println(ConsoleColor.BROWN+ "+---------------------------------+" + ConsoleColor.RESET);
			}
			
			if (flag == false) System.out.println(ConsoleColor.RED_BACKGROUND + "No tickets found" + ConsoleColor.RESET);
		}
		catch (SQLException s){
			System.out.println(ConsoleColor.RED_BACKGROUND + s.getMessage() + ConsoleColor.RESET);
		}
		
	}

	

}
