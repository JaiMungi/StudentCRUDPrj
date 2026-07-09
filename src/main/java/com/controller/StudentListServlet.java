package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.dao.StudentDAO;
import com.dao.StudentDAOImpl;
import com.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/viewstudents")
public class StudentListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String ctx = request.getContextPath();

        List<Student> students = studentDAO.getAllStudents();
        String msg = request.getParameter("msg");

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
        out.println("<title>View Students</title>");
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

        if (msg != null) {
            boolean ok = msg.endsWith("success");
            out.println("<div class='alert " + (ok ? "alert-success" : "alert-error") + "' style='margin-top:22px;'>"
                    + getMessage(msg) + "</div>");
        }

        out.println("<div class='page-wrap' style='padding-top:" + (msg != null ? "10" : "48") + "px;'>");
        out.println("<div class='card wide'>");
        out.println("<div class='card-header'><h1>📋 Registered Students</h1>"
                + "<p>" + students.size() + " record(s) found</p></div>");
        out.println("<div class='card-body'>");

        out.println("<div class='table-toolbar'>");
        out.println("<div class='search-box'>🔍 <input type='text' id='searchInput' "
                + "onkeyup='filterTable()' placeholder='Search by name or branch...'></div>");
        out.println("<a class='btn btn-primary btn-sm' href='" + ctx + "/registration.html'>➕ Add Student</a>");
        out.println("</div>");

        if (students.isEmpty()) {
            out.println("<div class='empty-state'><span class='emoji'>🗂️</span>"
                    + "No students registered yet.<br><a class='btn btn-primary btn-sm' style='margin-top:14px;' "
                    + "href='" + ctx + "/registration.html'>Register the first student</a></div>");
        } else {
            out.println("<table class='data-table' id='studentTable'>");
            out.println("<thead><tr><th>Student</th><th>Branch</th><th>Semester</th>"
                    + "<th>Gender</th><th>Hobbies</th><th>Actions</th></tr></thead><tbody>");

            for (Student s : students) {
                String initials = getInitials(s.getName());
                String genderClass = "Female".equalsIgnoreCase(s.getGender()) ? "gender-female" : "gender-male";

                out.println("<tr>");
                out.println("<td><div class='name-cell'><span class='avatar'>" + initials + "</span>"
                        + escape(s.getName()) + "</div></td>");
                out.println("<td><span class='badge'>" + escape(s.getBranch()) + "</span></td>");
                out.println("<td>" + escape(s.getSemester()) + "</td>");
                out.println("<td><span class='badge " + genderClass + "'>" + escape(s.getGender()) + "</span></td>");
                out.println("<td>" + renderHobbies(s.getHobby()) + "</td>");
                out.println("<td class='row-actions'>");
                out.println("<a class='btn btn-ghost btn-sm' href='" + ctx + "/editstudent?id=" + s.getId() + "'>✏️ Edit</a>");
                out.println("<a class='btn btn-danger btn-sm' href='" + ctx + "/deletestudent?id=" + s.getId() + "'>🗑️ Delete</a>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</tbody></table>");
        }

        out.println("</div></div></div>");

        out.println("<script>");
        out.println("function filterTable(){");
        out.println("  var q = document.getElementById('searchInput').value.toLowerCase();");
        out.println("  var rows = document.querySelectorAll('#studentTable tbody tr');");
        out.println("  rows.forEach(function(row){");
        out.println("    var text = row.innerText.toLowerCase();");
        out.println("    row.style.display = text.indexOf(q) > -1 ? '' : 'none';");
        out.println("  });");
        out.println("}");
        out.println("</script>");

        out.println("</body></html>");
    }

    private String getMessage(String code) {
        switch (code) {
            case "insert_success": return "✅ Student registered successfully!";
            case "insert_fail":    return "❌ Failed to register student.";
            case "update_success": return "✅ Student updated successfully!";
            case "update_fail":    return "❌ Failed to update student.";
            case "delete_success": return "✅ Student deleted successfully!";
            case "delete_fail":    return "❌ Failed to delete student.";
            default: return "";
        }
    }

    private String renderHobbies(String hobby) {
        if (hobby == null || hobby.trim().isEmpty()) {
            return "<span style='color:#aaa;font-size:12px;'>—</span>";
        }
        StringBuilder sb = new StringBuilder();
        for (String h : hobby.split("\\s*,\\s*")) {
            if (!h.isEmpty()) {
                sb.append("<span class='hobby-pill'>").append(escape(h)).append("</span>");
            }
        }
        return sb.toString();
    }

    private String getInitials(String name) {
        if (name == null || name.trim().isEmpty()) return "?";
        String[] parts = name.trim().split("\\s+");
        String initials = parts[0].substring(0, 1);
        if (parts.length > 1) {
            initials += parts[parts.length - 1].substring(0, 1);
        }
        return initials.toUpperCase();
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
