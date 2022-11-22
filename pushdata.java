package com.signup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet(value="/qwer")
public class Pushdata extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse rep) throws ServletException, IOException {
		String name=req.getParameter("nam");
		String email=req.getParameter("em");
		String mobile=req.getParameter("mob");
		String password=req.getParameter("pass");
		
		Connection cn=null;
		PreparedStatement ps=null;
		PrintWriter po=rep.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
				cn=DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false","root","abcd");
				ps=cn.prepareStatement("insert into signup.signupdetails values(?,?,?,?)");
				
				ps.setString(1,name);
				ps.setString(2,email);
				ps.setLong(3,Long.parseLong(mobile));
				ps.setString(4,password);
				
				ps.executeUpdate();
				
				po.println("<html><body><h2>sucessesfull</h2>"
						+ "<a href='login.html'>click to login</a>"
						+ "</body></html>");
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
			ps.close();
			cn.close();
			}
			catch(SQLException l) {
				System.out.println("NOT closed...");
			}
		}
		
	}

}

