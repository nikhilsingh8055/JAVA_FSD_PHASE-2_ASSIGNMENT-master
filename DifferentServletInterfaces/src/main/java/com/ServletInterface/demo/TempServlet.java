package com.ServletInterface.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TempServlet
 */
@WebServlet("/TempServlet")
public class TempServlet extends HttpServlet {
	

	private ServletConfig config=null;
	@Override
	public void destroy() {
		config=null;
		System.out.println("destroy called");
		
	}

	@Override
	public ServletConfig getServletConfig() {
		return config;
	}

	@Override
	public String getServletInfo() {
		return config.getServletName();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Init called");
		this.config=config;
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Service called");
		PrintWriter out=resp.getWriter();
		out.print("This is my servlet");
	}

}