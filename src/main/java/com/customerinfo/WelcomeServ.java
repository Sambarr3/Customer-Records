package com.customerinfo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("<title>Welcome</title>");
		out.println("<link rel=\"stylesheet\" href=\"welstyles.css\">"); // Link to external CSS
		out.println("</head>");
		out.println("<body>");

		out.println("<div class=\"content-container\">");
		out.println("<h2>Welcome to the Servlet!</h2>");
		out.println("<form action=\"read\" method=\"post\">");
		out.println("<input type=\"submit\" name=\"btn\" class=\"submit-btn\" value=\"See Records\">");
		out.println("</form>");
		out.println("</div>");

		out.println("</body>");
		out.println("</html>");

	}

}
