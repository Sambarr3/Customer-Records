package com.customerinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;

	public void init(ServletConfig config) throws ServletException {

		String url = config.getServletContext().getInitParameter("dburl");
		String user = config.getServletContext().getInitParameter("dbuser");
		String pass = config.getServletContext().getInitParameter("dbpass");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id,name,email,created_at FROM registration_db.users");

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			out.println("<html><head>");
			out.println("<link rel='stylesheet' type='text/css' href='/Customer_Records/ReadStyles.css'>");
			out.println("</head><body>");
			out.println("<h2 style='color:green;'>Records that Found:</h2>");
			out.println(
					"<table class='record-table'><tr><th>ID</th><th>Name</th><th>Email</th><th>Joining Date</th></tr>");

			while (rs.next()) {
				out.println("<tr><td>" + rs.getInt("id") + "</td>");
				out.println("<td>" + rs.getString("name") + "</td>");
				out.println("<td>" + rs.getString("email") + "</td>");
				out.println("<td>" + rs.getDate("created_at") + "</td></tr>");
			}
			out.println("</table>");
			out.println("</body></html>");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
