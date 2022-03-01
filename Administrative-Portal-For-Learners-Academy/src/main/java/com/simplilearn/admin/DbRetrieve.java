package com.simplilearn.admin;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.simplilearn.models.Student;
import com.simplilearn.models.Subject;
import com.simplilearn.models.Teacher;
import com.simplilearn.models.Class;

public class DbRetrieve {

	private DataSource dataSource;

	public DbRetrieve(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Student> getStudents() {

		List<Student> students = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			// Get a connection
			myConn = dataSource.getConnection();

			// Create sql stmt to get data from database
			String sql = "SELECT * FROM students";
			myStmt = myConn.createStatement();

			// Execute query
			myRs = myStmt.executeQuery(sql);

			// Process result
			while (myRs.next()) {

				// Retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("fname");
				String lastName = myRs.getString("lname");
				int age = myRs.getInt("age");
				int aclass = myRs.getInt("class");

				// Create new student object
				Student tempStudent = new Student(id, firstName, lastName, age, aclass);

				// Add it to the list of students
				students.add(tempStudent);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return students;

	}

	public List<Teacher> getTeachers() {

		List<Teacher> teachers = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			// Get a connection
			myConn = dataSource.getConnection();

			// Create sql stmt
			String sql = "SELECT * FROM teachers";
			myStmt = myConn.createStatement();

			// Execute query
			myRs = myStmt.executeQuery(sql);

			// Process result
			while (myRs.next()) {

				// Retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("fname");
				String lastName = myRs.getString("lname");
				int age = myRs.getInt("age");

				// Create new student object
				Teacher temp = new Teacher(id, firstName, lastName, age);

				// Add it to the list of students
				teachers.add(temp);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// Close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return teachers;

	}

	public List<Subject> getSubjects() {

		List<Subject> subjects = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			// Get a connection
			myConn = dataSource.getConnection();

			// Create sql stmt
			String sql = "SELECT * FROM subjects";
			myStmt = myConn.createStatement();

			// Execute query
			myRs = myStmt.executeQuery(sql);

			// Process the result
			while (myRs.next()) {

				// Retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String shortcut = myRs.getString("shortcut");

				// Create new student object
				Subject temp = new Subject(id, name,shortcut);

				// Add it to the list of students
				subjects.add(temp);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
			// Close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return subjects;

	}

	public List<Class> getClasses() {

		List<Class> classes = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			// Get a connection
			myConn = dataSource.getConnection();

			// Create sql stmt
			String sql = "SELECT * FROM classes";
			myStmt = myConn.createStatement();

			// Execute query
			myRs = myStmt.executeQuery(sql);

			// Process result
			while (myRs.next()) {

				// Retrieve data from result set row
				int id = myRs.getInt("id");
				int section = myRs.getInt("section");
				int subject = myRs.getInt("subject");
				int teacher = myRs.getInt("teacher");
				String time = myRs.getString("time");

				Teacher tempTeacher = loadTeacher(teacher);
				Subject tempSubject = loadSubject(subject);

				String teacher_name = tempTeacher.getFname() + " " + tempTeacher.getLname();

				// Create new student object
				Class temp = new Class(id, section, teacher_name, tempSubject.getName(), time);

				// Add it to the list of students
				classes.add(temp);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// Close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return classes;

	}

	public Teacher loadTeacher(int teacherId) {

		Teacher theTeacher = null;

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			// Get a connection
			myConn = dataSource.getConnection();

			// Create sql stmt
			String sql = "SELECT * FROM teachers WHERE id = " + teacherId;
			myStmt = myConn.createStatement();

			// Execute query
			myRs = myStmt.executeQuery(sql);

			// Process result
			while (myRs.next()) {

				// Retrieve data from result set row
				int id = myRs.getInt("id");
				String fname = myRs.getString("fname");
				String lname = myRs.getString("lname");
				int age = myRs.getInt("age");
				theTeacher = new Teacher(id, fname, lname, age);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return theTeacher;

	}

	public Subject loadSubject(int subjectId) {

		Subject theSubject = null;

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			// Get a connection
			myConn = dataSource.getConnection();

			// Create sql stmt
			String sql = "SELECT * FROM subjects WHERE id = " + subjectId;
			myStmt = myConn.createStatement();

			// Execute query
			myRs = myStmt.executeQuery(sql);

			// Process result
			while (myRs.next()) {

				// Retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String shortcut = myRs.getString("shortcut");

				theSubject = new Subject(id, name,shortcut);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return theSubject;

	}

	public Class loadClass(int classId) {

		Class theClass = null;

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			// Get a connection
			myConn = dataSource.getConnection();

			// Create sql stmt
			String sql = "SELECT * FROM clasess WHERE id = " + classId;
			myStmt = myConn.createStatement();

			// Execute query
			myRs = myStmt.executeQuery(sql);

			// Process result
			while (myRs.next()) {

				// Retrieve data from result set row
				int id = myRs.getInt("id");
				int section = myRs.getInt("section");
				int subject = myRs.getInt("subject");
				int teacher = myRs.getInt("teacher");
				String time = myRs.getString("time");

				Teacher tempTeacher = loadTeacher(teacher);
				Subject tempSubject = loadSubject(subject);

				String teacher_name = tempTeacher.getFname() + " " + tempTeacher.getLname();

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return theClass;

	}

	public List<Student> loadClassStudents(int classId) {

		List<Student> students = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			// Get a connection
			myConn = dataSource.getConnection();

			// Create sql stmt
			String sql = "SELECT * FROM students WHERE class = " + classId;
			myStmt = myConn.createStatement();

			// Execute query
			myRs = myStmt.executeQuery(sql);

			// Process result
			while (myRs.next()) {

				// Retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("fname");
				String lastName = myRs.getString("lname");
				int age = myRs.getInt("age");
				int aclass = myRs.getInt("class");

				// Create new student object
				Student tempStudent = new Student(id, firstName, lastName, age, aclass);
				students.add(tempStudent);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return students;

	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}