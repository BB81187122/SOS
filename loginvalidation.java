package com.signup;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet(value="/log")
public class loginvalidation extends GenericServlet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void service(ServletRequest req, ServletResponse rep) throws ServletException, IOException {
		String email=req.getParameter("em");
		String password=req.getParameter("pass");
		
		Connection cn=null;
		Statement ps=null;
		ResultSet rs=null;
		PrintWriter po=rep.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false","root","abcd");
			ps=cn.createStatement();
			rs=ps.executeQuery("select * from signup.signupdetails where email='"+email+"' and password='"+password+"'");
			
			if(rs.next())
			{
				po.println("<html><body><h1>login Succesfull...</h1>"
						+ "</body></html>");
			}
			else
			{
				po.println("<html><body><h1>Please Retry With Your Credentials....</h1>"
						+ "</body></html>");
			}
		}
		catch(ClassNotFoundException |SQLException K) {
			System.out.println("not working.....");
		}
		finally
		{
			try {
					ps.close();
					cn.close();
					
				}
			catch( SQLException e)
			{
				System.out.println("NOT closed...");
			}
			}
		}
		
	}



