package com.simplilearn.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.simplilearn.models.Student;
import com.simplilearn.models.Subject;
import com.simplilearn.models.Teacher;
import com.simplilearn.models.Class;

/**
 * Servlet implementation class AdminControllerServlet
 */
@WebServlet("/AdminControllerServlet")
public class AdminControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DbRetrieve dbRetrieve;

	@Resource(name = "jdbc_database")
	private DataSource datasource;

	@Override
	public void init() throws ServletException {

		super.init();

		// Create instance of Database util, to pass in conn pool object
		try {
			dbRetrieve = new DbRetrieve(datasource);

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminControllerServlet() {
		super();
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {

			// Read the "command" parameter
			String command = request.getParameter("command");

			if (command == null) {
				command = "CLASSES";
			}
			
			// If no Cookies
			if (!getCookies(request, response) && (!command.equals("LOGIN"))) {

				response.sendRedirect("/Administrative-Portal/login.jsp");
			}

			else {

				// If there is no command, how to handle
				// Route the data to the appropriate method
				switch (command) {

				case "STUDENTS":
					studentsList(request, response);
					break;

				case "TEACHERS":
					teachersList(request, response);
					break;

				case "SUBJECTS":
					subjectList(request, response);
					break;

				case "CLASSES":
					classestList(request, response);
					break;

				case "ST_LIST":
					classStudentsList(request, response);
					break;

				case "LOGIN":
					login(request, response);
					break;

				default:
					classestList(request, response);
				}
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
		// Response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void studentsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Get students from Database util
		List<Student> students = dbRetrieve.getStudents();

		// Add students to the request
		request.setAttribute("STUDENT_LIST", students);

		// Send it to the JSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}

	private void teachersList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Get students from Database util
		List<Teacher> teachers = dbRetrieve.getTeachers();

		// Add students to the request
		request.setAttribute("TEACHERS_LIST", teachers);

		// Send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teachers-list.jsp");
		dispatcher.forward(request, response);
	}

	private void subjectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Get subjects from Database util
		List<Subject> subjects = dbRetrieve.getSubjects();

		// Add subjects to the request
		request.setAttribute("SUBJECTS_LIST", subjects);

		// Send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/subjects-list.jsp");
	dispatcher.forward(request, response);
	}

	private void classestList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Get subjects from Database util
		List<Class> classes = dbRetrieve.getClasses();

		// Add subjects to the request
		request.setAttribute("CLASSES_LIST", classes);

		// Send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/classes-list.jsp");
		dispatcher.forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username.toLowerCase().equals("ravikant") && password.toLowerCase().equals("ravi123")) {

			Cookie cookie = new Cookie(username, password);

			// Setting the maximum age to 1 day
			cookie.setMaxAge(86400); // 86400 seconds in a day

			// Send the cookie to the client
			response.addCookie(cookie);
			classestList(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void classStudentsList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int classId = Integer.parseInt(request.getParameter("classId"));
		String section = request.getParameter("section");
		String subject = request.getParameter("subject");

		// Get subjects from Database util
		List<Student> students = dbRetrieve.loadClassStudents(classId);

		// Add subjects to the request
		request.setAttribute("STUDENTS_LIST", students);
		request.setAttribute("SECTION", section);
		request.setAttribute("SUBJECT", subject);

		// Send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/class-students.jsp");
		dispatcher.forward(request, response);

	}
	private boolean getCookies(HttpServletRequest request, HttpServletResponse response) throws Exception {

		boolean check = false;
		Cookie[] cookies = request.getCookies();
		// Find the cookie of interest in arrays of cookies
		for (Cookie cookie : cookies) {
			 
			if (cookie.getName().equals("ravikant") && cookie.getValue().equals("ravi123")) {
				check = true;
				break;
			} 
		}
		
		return check;
	}
}