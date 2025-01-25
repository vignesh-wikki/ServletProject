package com.OnlineTrainingSystem.Controller;

import com.OnlineTrainingSystem.DAO.Repositary;
import com.OnlineTrainingSystem.Model.Course;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addcourse")
public class CourseController extends HttpServlet {
    private Repositary repo;
    private Course newCourse;

    public void init() throws ServletException {
        super.init();
        this.repo = new Repositary();
        this.newCourse = new Course();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            newCourse.setCourseName(request.getParameter("coursename"));
            newCourse.setCourseDescription(request.getParameter("coursedescription"));
            repo.setNewCourse(newCourse);
            boolean addcourse = repo.requestCourseRegister();

            if (addcourse) response.sendRedirect("/courses");
            else {
                request.setAttribute("error", "Invalid course input");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/course");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        this.repo = null;
        this.newCourse = null;
    }

}

