package com.dao;

import java.util.List;
import com.model.Student;

/**
 * DAO — contract for CRUD operations on the student table.
 */
public interface StudentDAO {
    boolean insertStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(int id);
    Student getStudentById(int id);
    List<Student> getAllStudents();
}
