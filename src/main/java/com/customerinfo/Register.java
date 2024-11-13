package com.customerinfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;

	public void init(ServletConfig config) throws ServletException {

		ServletContext context = config.getServletContext();

		String url = context.getInitParameter("dburl");
		String user = context.getInitParameter("dbuser");
		String pass = context.getInitParameter("dbpass");

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

		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPass = request.getParameter("confirm-password");

		if (!password.equals(confirmPass)) {
			response.sendRedirect("error.html?error=password_mismatch");
			return;
		}

		try {
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO registration_db.users (name,username,phone,email,password) values(?,?,?,?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, username);
			pstmt.setString(3, phone);
			pstmt.setString(4, email);
			pstmt.setString(5, password);

			pstmt.executeUpdate();
			response.sendRedirect("success.html");

		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				response.sendRedirect("error.html?error=duplicate_entry");
			} else {
				e.printStackTrace();
				response.getWriter().println("Database error: " + e.getMessage());
			}
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
