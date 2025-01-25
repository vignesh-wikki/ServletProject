package com.OnlineTrainingSystem.Controller;

import com.OnlineTrainingSystem.DAO.Repositary;
import com.OnlineTrainingSystem.Model.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/course")
public class GetCourse extends HttpServlet {
    private Repositary repo;
    private Course newCourse;

    @Override
    public void init() throws ServletException {
        super.init();
        this.repo = new Repositary();
        this.newCourse = new Course();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (Boolean.TRUE.equals(session.getAttribute("isLogedin"))) {
            try {
                repo.setNewCourse(newCourse);
                List<Course> list = repo.getAllCources();
                request.setAttribute("courseList", list);
                request.getRequestDispatcher("course.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
        } else response.sendRedirect("login.jsp");
    }

    @Override
    public void destroy() {
        super.destroy();
        this.repo = null;
        this.newCourse = null;
    }
}
