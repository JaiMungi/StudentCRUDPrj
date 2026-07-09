package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;



import com.dao.StudentDAO;
import com.dao.StudentDAOImpl;
import com.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editstudent")
public class EditServlet extends HttpServlet {

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
            redirectNotFound(response, ctx);
            return;
        }

        Student s = studentDAO.getStudentById(id);
        if (s == null) {
            redirectNotFound(response, ctx);
            return;
        }

        List<String> hobbies = Arrays.asList(
                s.getHobby() != null && !s.getHobby().isEmpty() ? s.getHobby().split("\\s*,\\s*") : new String[0]);

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
        out.println("<title>Edit Student</title>");
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
        out.println("<div class='card-header'><h1>✏️ Edit Student</h1><p>Update the details for "
                + escape(s.getName()) + "</p></div>");
        out.println("<div class='card-body'>");

        out.println("<form action='" + ctx + "/updatestudent' method='post'>");
        out.println("<input type='hidden' name='id' value='" + s.getId() + "'>");

        out.println("<div class='form-group'><label for='name'>Name</label>"
                + "<input type='text' id='name' name='name' value='" + escape(s.getName()) + "' required></div>");

        out.println("<div class='form-group'><label for='branch'>Branch</label><select id='branch' name='branch'>");
        for (String b : new String[]{"CSE", "IT", "ECE", "EE", "ME", "CE"}) {
            out.println("<option " + (b.equals(s.getBranch()) ? "selected" : "") + ">" + b + "</option>");
        }
        out.println("</select></div>");

        out.println("<div class='form-group'><label for='semester'>Semester</label><select id='semester' name='semester'>");
        for (String sem : new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII"}) {
            out.println("<option " + (sem.equals(s.getSemester()) ? "selected" : "") + ">" + sem + "</option>");
        }
        out.println("</select></div>");

        out.println("<div class='form-group'><label>Gender</label><div class='choice-row'>");
        out.println("<label><input type='radio' name='gender' value='Male' "
                + ("Male".equals(s.getGender()) ? "checked" : "") + "> Male</label>");
        out.println("<label><input type='radio' name='gender' value='Female' "
                + ("Female".equals(s.getGender()) ? "checked" : "") + "> Female</label>");
        out.println("</div></div>");

        out.println("<div class='form-group'><label>Hobby</label><div class='choice-row'>");
        for (String h : new String[]{"Reading", "Playing", "Dancing"}) {
            out.println("<label><input type='checkbox' name='hobby' value='" + h + "' "
                    + (hobbies.contains(h) ? "checked" : "") + "> " + h + "</label>");
        }
        out.println("</div></div>");

        out.println("<div class='actions-row'>");
        out.println("<button type='submit' class='btn btn-primary btn-block'>SAVE CHANGES</button>");
        out.println("</div>");
        out.println("</form>");

        out.println("<div style='text-align:center;margin-top:14px;'>"
                + "<a href='" + ctx + "/viewstudents' style='color:#6b7280;font-size:13px;text-decoration:none;'>← Back to list</a></div>");

        out.println("</div></div></div>");
        out.println("</body></html>");
    }

    private void redirectNotFound(HttpServletResponse response, String ctx) throws IOException {
        response.sendRedirect(ctx + "/viewstudents?msg=update_fail");
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("'", "&#39;");
    }
}
