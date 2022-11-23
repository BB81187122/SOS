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
@WebServlet(value="/disc")
public class addcoupon extends GenericServlet {

	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
			String coupon_id=req.getParameter("cna");
			String email=req.getParameter("dem");
			String discount=req.getParameter("dis");
			
			Connection cn=null;
			PreparedStatement ps=null;
			PrintWriter po=res.getWriter();
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
					cn=DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false","root","abcd");
					ps=cn.prepareStatement("insert into signup.coupon_details values(?,?,?)");
					
					ps.setInt(1,Integer.parseInt(coupon_id));
					ps.setString(2,email);
					ps.setString(3,discount);
					
					ps.executeUpdate();
					
					po.println("<html><style>h2{color:green; text-align: center;}</style>"
							+ "<body><h2>sucessesfull</h2>"
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

