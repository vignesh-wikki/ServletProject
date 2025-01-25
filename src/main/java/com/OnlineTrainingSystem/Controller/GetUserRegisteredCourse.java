package com.OnlineTrainingSystem.Controller;

import com.OnlineTrainingSystem.DAO.Repositary;
import com.OnlineTrainingSystem.Model.Course;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/studentCourse")
public class GetUserRegisteredCourse extends HttpServlet {
    private Repositary repo;

    @Override
    public void init() throws ServletException {
        super.init();
        this.repo = new Repositary();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession();
            String userEmail = (String) session.getAttribute("userEmail");
            Map<String, List<Course>> courseList = repo.getUserRegisteredCourse(userEmail);
            request.setAttribute("registeredCourses", courseList);
            request.setAttribute("courseList", courseList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("studentCourse.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        this.repo = null;
    }
}
