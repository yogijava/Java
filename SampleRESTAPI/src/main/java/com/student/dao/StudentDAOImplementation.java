package com.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.student.model.Student;
import com.student.util.DBUtil;

public class StudentDAOImplementation implements StudentDAO {

	private Connection conn;

	public StudentDAOImplementation() {
		conn = DBUtil.getConnection();
	}
	@Override
	public Student addStudent( Student student ) {
		System.out.println("POST_______________>");
		try {
			System.out.println("insert into DB");
			String query = "insert into student (firstName, lastName, course, year) values (?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			preparedStatement.setString( 1, student.getFirstName() );
			preparedStatement.setString( 2, student.getLastName() );
			preparedStatement.setString( 3, student.getCourse() );
			preparedStatement.setInt( 4, student.getYear() );
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
		
	}
	@Override
	public void deleteStudent( int studentId ) {
		try {
			String query = "delete from student where studentId=?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, studentId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Student updateStudent( Student student ) {
		try {
			String query = "update student set firstName=?, lastName=?, course=?, year=? where studentId=?";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			preparedStatement.setString( 1, student.getFirstName() );
			preparedStatement.setString( 2, student.getLastName() );
			preparedStatement.setString( 3, student.getCourse() );
			preparedStatement.setInt( 4, student.getYear() );
			preparedStatement.setInt(5, student.getStudentId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	@Override
	public List<Student> getAllStudents() {
		System.out.println("going to DAOIMp");
		List<Student> students = new ArrayList<Student>();
		System.out.println("--------------------");
		try {
			System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;");
			Statement statement = conn.createStatement();
			System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;");
			System.out.println("connectioned to db");
			ResultSet resultSet = statement.executeQuery( "select * from student" );
			System.out.println("going to db");
			while( resultSet.next() ) {
				Student student = new Student();
				student.setStudentId( resultSet.getInt( "studentId" ) );
				student.setFirstName( resultSet.getString( "firstName" ) );
				student.setLastName( resultSet.getString( "lastName" ) );
				student.setCourse( resultSet.getString( "course" ) );
				student.setYear( resultSet.getInt( "year" ) );
				students.add(student);
				System.out.println(student);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
		}
		

	/*@Override
	public List<Student> getAllStudents() {
		
		System.out.println("going to DAOIMp");
		List<Student> students = new ArrayList<Student>();
		System.out.println("--------------------");
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/UserDB","root","root");
			System.out.println("connection establised");
			
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet resultSet=stmt.executeQuery("select * from student");  
			System.out.println("going to db");
			while( resultSet.next() ) {
				Student student = new Student();
				student.setStudentId( resultSet.getInt( "studentId" ) );
				student.setFirstName( resultSet.getString( "firstName" ) );
				student.setLastName( resultSet.getString( "lastName" ) );
				student.setCourse( resultSet.getString( "course" ) );
				student.setYear( resultSet.getInt( "year" ) );
				students.add(student);
				System.out.println(students);
			}
			resultSet.close();
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
		
		return students;
		
	}*/
	


	@Override
	public Student getStudentById(int studentId) {
		Student student = new Student();
		System.out.println("iddddddddddddddd valueeeeeeeeeeee");
		try {
			String query = "select * from student where studentId=?";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			preparedStatement.setInt(1, studentId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while( resultSet.next() ) {
				student.setStudentId( resultSet.getInt( "studentId" ) );
				student.setFirstName( resultSet.getString( "firstName" ) );
				student.setLastName( resultSet.getString( "LastName" ) );
				student.setCourse( resultSet.getString( "course" ) );
				student.setYear( resultSet.getInt( "year" ) );
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

}