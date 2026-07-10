package com.controller;
import com.dao.StudentDAOImpl;

import java.io.IOException;

import com.model.Student;
import com.dao.StudentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("https://studentcrudprj-production.up.railway.app/register")
public class RegisterServlet extends jakarta.servlet.http.HttpServlet {

    private static final long serialVersionUID = 1L;
    private final StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String branch = request.getParameter("branch");
        String semester = request.getParameter("semester");
        String gender = request.getParameter("gender");
        String[] hobbies = request.getParameterValues("hobby"); // checkboxes -> array

        String hobbyStr = (hobbies != null) ? String.join(", ", hobbies) : "";

        Student student = new Student();
        student.setName(name);
        student.setBranch(branch);
        student.setSemester(semester);
        student.setGender(gender);
        student.setHobby(hobbyStr);

        boolean success = studentDAO.insertStudent(student);

        response.sendRedirect(request.getContextPath()
                + "/viewstudents?msg=" + (success ? "insert_success" : "insert_fail"));
    }
}
