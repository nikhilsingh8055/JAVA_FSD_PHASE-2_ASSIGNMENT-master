package com.ServletInterface.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Generic
 */
@WebServlet("/Generic")
public class Generic extends HttpServlet {
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		
		out.print("Outpur From Generic Servlet");
		
	}

}
