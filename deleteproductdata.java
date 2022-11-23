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
@WebServlet(value="/dele")
public class deleteproductdata extends GenericServlet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String product_id=req.getParameter("did");
		int value=Integer.parseInt(product_id);
		Connection cn=null;
		PreparedStatement ps=null;
		PrintWriter po=res.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
				cn=DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false","root","abcd");
				ps=cn.prepareStatement("delete from signup.product_table where product_id="+value+"");
				
				ps.executeUpdate();
				
				po.println("<html><style>h3{color:green; text-align: center;}</style>"
						+ "</head>"
						+ "<body><h3>login Succesfull...</h3>"
						+ "</body></html>");
		}
		 catch (ClassNotFoundException | SQLException e) {
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
