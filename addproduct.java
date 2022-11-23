package com.signup;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(value="/paro")
public class addproduct extends GenericServlet {

	public static void main(String[] args) {
	

	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		         String product_id=req.getParameter("pid");
		         String product_name=req.getParameter("pna");
		         String product_price=req.getParameter("ppr");
		         String product_img=req.getParameter("pim");
		         
		         Connection cn=null;
		 		PreparedStatement ps=null;
		 		PrintWriter po=res.getWriter();
		         
		 		
		 		try {
		 			Class.forName("com.mysql.cj.jdbc.Driver");
		 			
		 				cn=DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false","root","abcd");
		 				ps=cn.prepareStatement("insert into signup.product_table values(?,?,?,?)");
		 				
		 				ps.setInt(1,Integer.parseInt(product_id));
		 				ps.setString(2,product_name);
		 				ps.setString(3,product_price);
		 				ps.setString(4,product_img);
		 				
		 				ps.executeUpdate();
		 				po.println("<html><style>h2{color:green; text-align: center;}</style>"
		 						+ "<body><h2>sucessesfull</h2>"
		 						+ "</body></html>");
		 				
		 		} catch (ClassNotFoundException | SQLException e) {
		 			System.out.println("no...");
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
