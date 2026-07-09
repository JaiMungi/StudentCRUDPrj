package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.dao.StudentDAO;
import com.dao.StudentDAOImpl;
import com.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deletestudent")
public class DeleteServlet extends jakarta.servlet.http.HttpServlet {

    private static final long serialVersionUID = 1L;
    private final StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String ctx = request.getContextPath();

        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendRedirect(ctx + "/viewstudents");
            return;
        }

        Student s = studentDAO.getStudentById(id);
        if (s == null) {
            response.sendRedirect(ctx + "/viewstudents?msg=delete_fail");
            return;
        }

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
        out.println("<title>Delete Student</title>");
        out.println("<link rel='preconnect' href='https://fonts.googleapis.com'>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap' rel='stylesheet'>");
        out.println("<link rel='stylesheet' href='" + ctx + "/assets/style.css'>");
        out.println("</head><body>");

        out.println("<nav class='navbar'>");
        out.println("<a class='brand' href='" + ctx + "/index.html'>🎓 Student Portal</a>");
        out.println("<div class='nav-links'>");
        out.println("<a href='" + ctx + "/registration.html'>Register</a>");
        out.println("<a href='" + ctx + "/viewstudents'>View Students</a>");
        out.println("</div></nav>");

        out.println("<div class='page-wrap center-v'>");
        out.println("<div class='card'>");
        out.println("<div class='card-header danger'><h1>🗑️ Delete Student</h1>"
                + "<p>This action cannot be undone</p></div>");
        out.println("<div class='card-body'>");

        out.println("<p style='margin-top:0;color:#444;'>Are you sure you want to permanently delete "
                + "the following record?</p>");

        out.println("<dl class='confirm-summary'>");
        out.println("<dt>Name</dt><dd>" + escape(s.getName()) + "</dd>");
        out.println("<dt>Branch</dt><dd>" + escape(s.getBranch()) + "</dd>");
        out.println("<dt>Semester</dt><dd>" + escape(s.getSemester()) + "</dd>");
        out.println("<dt>Gender</dt><dd>" + escape(s.getGender()) + "</dd>");
        out.println("<dt>Hobby</dt><dd>" + (s.getHobby() == null || s.getHobby().isEmpty()
                ? "—" : escape(s.getHobby())) + "</dd>");
        out.println("</dl>");

        out.println("<form action='" + ctx + "/deletestudent' method='post'>");
        out.println("<input type='hidden' name='id' value='" + s.getId() + "'>");
        out.println("<div class='actions-row'>");
        out.println("<button type='submit' class='btn btn-danger btn-block'>YES, DELETE</button>");
        out.println("<a class='btn btn-ghost btn-block' href='" + ctx + "/viewstudents'>CANCEL</a>");
        out.println("</div>");
        out.println("</form>");

        out.println("</div></div></div>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ctx = request.getContextPath();
        int id = Integer.parseInt(request.getParameter("id"));
        boolean success = studentDAO.deleteStudent(id);

        response.sendRedirect(ctx + "/viewstudents?msg=" + (success ? "delete_success" : "delete_fail"));
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
