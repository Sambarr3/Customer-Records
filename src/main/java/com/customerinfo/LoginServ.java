package com.customerinfo;

import java.io.IOException;
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

public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;

	public void init(ServletConfig config) throws ServletException {
		String url = config.getServletContext().getInitParameter("dburl");
		String user = config.getServletContext().getInitParameter("dbuser");
		String pass = config.getServletContext().getInitParameter("dbpass");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM users WHERE email='" + username + "' AND password='" + password + "'");

			if (rs.next()) {
				response.sendRedirect("welcome");
			}else {
				response.sendRedirect("login.html?error=BAD CREDENTIALS, PLEASE TRY AGAIN!");
			}

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
