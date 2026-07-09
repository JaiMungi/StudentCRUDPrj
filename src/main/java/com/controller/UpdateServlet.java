package com.controller;

import java.io.IOException;


import com.dao.StudentDAO;
import com.dao.StudentDAOImpl;
import com.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/updatestudent")
public class UpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String branch = request.getParameter("branch");
        String semester = request.getParameter("semester");
        String gender = request.getParameter("gender");
        String[] hobbies = request.getParameterValues("hobby");
        String hobbyStr = (hobbies != null) ? String.join(", ", hobbies) : "";

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setBranch(branch);
        student.setSemester(semester);
        student.setGender(gender);
        student.setHobby(hobbyStr);

        boolean success = studentDAO.updateStudent(student);

        response.sendRedirect(request.getContextPath()
                + "/viewstudents?msg=" + (success ? "update_success" : "update_fail"));
    }
}
