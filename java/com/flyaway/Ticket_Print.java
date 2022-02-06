package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ticket_Print extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			PrintWriter out= response.getWriter();
		response.setContentType("text/html");
		//out.println("<h2>welcome your Flight_Price</h2>");
		
		
		String flight_no=request.getParameter("agree");
		//out.println(flight_no);
		try {Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/flyaway","root","Shubham@1234567890");
		String q2= ("insert into booking1(t_date,trav_no,flight_no) values( ?, ?, ?)");
		PreparedStatement pstmt2= con.prepareStatement(q2);
		//for booking id fetch;
		String S1= ("select * from booking1 order by booking_no desc limit 1");
		PreparedStatement pstmt3= con.prepareStatement(S1);
		
		String S2= ("select * from enquiry order by id desc limit 1");
		
		
		PreparedStatement pstmt1= con.prepareStatement(S2);
		ResultSet rs1=pstmt1.executeQuery();
		
		int id=0;
		String source=null;
		String destination=null;
		String travel_date=null;
		int no_of_travelers=0;
		String route_id=null;
		
		while(rs1.next())
		
		{
			
			id =rs1.getInt(1);
			source =rs1.getString(2);
			destination =rs1.getString(3);
			travel_date =rs1.getString(4);
			no_of_travelers=rs1.getInt(5);
			route_id=rs1.getString(6);
			//out.println(user_id+" HI   " +user_scity+"   "+user_dcity+"     "+user_date+"     "+user_no+ "  "+user_rid+"<br>");
			
		}
		
		
		pstmt1.close();
		
		String q= ("select * from flight where route_id= ?");
		 PreparedStatement pstmt= con.prepareStatement(q);
		 
		 pstmt.setString(1, route_id);
		 ResultSet rs=pstmt.executeQuery();
		 
		 String val=null;
		 double  val1=0;
		 double val2=0;
		 String val3=null;
		 String val4=null;
		 
		 	 out.println("<center>");
		     out.println("<form action=\"Final.html\" method=\"post\">" );
			 out.println("<h1>");
			 out.println("<table id = \"main_section\" cellspacing=\"4\" bgcolor=\"yellow\" > ");
			 out.println ("<tr> ");
				/*
				 * out.println("<td >FLIGHT NUMBER</td>");
				 * out.println("<td >PRICE</td>");
				 * out.println("<td >AVAILABLE SEATS</td>");
				 * out.println("<td >ARIVAL TIME </td>");
				 * out.println("<td >DEPARTURE TIME </td>");
				 * out.println("<td >PLEASE SELECT</td>");
				 */out.println("</tr>");
			 
			 
			 while(rs.next())  
			 {  
				 
				 out.println("<tr>");
				 out.println("<center>");
				  val=rs.getString(1);
				  val1=rs.getInt(2);
				  val2=rs.getInt(3);
				  val3=rs.getString(4);
				  val4=rs.getString(5);
			    // out.println("<h2><td > " + val + "</td>"+"<td > " + val1 + "</td>"+"<td > " + val2 + "</td>"+"<td > " + val3 
			    	//	 + "</td>"+"<td > " + val4 + "</td>"+"<td><input type=\"radio\" name=\"agree\" value=\"AI101\"></td></h2");
			     out.println("</center>"); 
			     out.println("</tr>");
			     
			     	
			      
			 
			     out.println("</tr>");
			     out.println("</form>");
			     out.println("</center>");
			     
			 }
			   //enter value in booking1;
			    pstmt2.setString(1, travel_date);
				pstmt2.setInt(2, no_of_travelers);
				pstmt2.setString(3, val);
				pstmt2.executeUpdate();
				
				// fetch booking id from booking1;
				ResultSet rs3=pstmt3.executeQuery();
				int booking_id=0;
				while(rs3.next())
					
				{
					
					booking_id =rs3.getInt(1);
				}
				
				
				// find
			     	
			  // REPORT SHOW 
			 out.println("<body text=\"green\">");
			 out.println("<tr text=\"red\">");
			 out.println("<h3>");
			 		out.println("<td>YOUR BOOKING NUMBER</td>");
			        out.println("<td>YOUR FLIGHT NUMBER</td>");
			     	out.println("<td>YOUR SOURCE CITY</td>"); 
			     	out.println("<td>YOUR DESTINATION CITY</td>");
			     	out.println("<td>JOURNEY DATE</td>");
			     	out.println("<td>NUMBER OF PASSENGER</td> ");
			     	out.println("<td>FLIGHT ARRIVAL TIME</td> ");
			     	out.println("<td>FLIGHT DEPARTURE TIME </td>");
			     	out.println("<td>AVAILABLE SEATS </td>");
			     	out.println("<td>TOTAL PRICE </td>");
			     	out.println("</h3>");
			     	out.println("</tr>");
			    out.println("<tr>");
			    out.println("<center>");
			    	out.println("<td><h3><MARQUEE>"+ booking_id+ "</MARQUEE></h3></td>");
			     	out.println("<td>"+ val+ "</td>");
			     	out.println("<td>"+ source +"</td>"); 
			     	out.println("<td>"+ destination +"</td>");
			     	out.println("<td>"+ travel_date +"</td>");
			     	out.println("<td> "+ no_of_travelers+"</td>");
			     	out.println("<td>"+ val3+"</td>");
			     	out.println("<td>"+ val4+"</td>");
			     	out.println("<td>"+ val2+"</td>");
			     	out.println("<td>RS "+ val1*no_of_travelers+"</td>");
			     	out.println("</center>");
			     	out.println("</body>");
			     	//out.println("</tr>");
			     RequestDispatcher rd=request.getRequestDispatcher("print.html");
				 rd.include(request, response);
			 
		 
						
				 pstmt.close();	 
		}
		
		
		catch (Exception e) {
		
		e.printStackTrace();
		out.println("<h1>"+ e +"</h1>");
		
		}
		

		
		
		
		
		
	}


}