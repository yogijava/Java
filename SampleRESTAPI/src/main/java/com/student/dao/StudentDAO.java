package com.student.dao;

import java.util.List;

import com.student.model.Student;

public interface StudentDAO {
	public Student addStudent( Student student );
	public void deleteStudent( int studentId );
	public Student updateStudent( Student student );
	public List<Student> getAllStudents();
	public Student getStudentById( int studentId );
}