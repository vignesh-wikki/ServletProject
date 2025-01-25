package com.OnlineTrainingSystem.Controller;

import com.OnlineTrainingSystem.DAO.Repositary;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/editCourse")
public class UpdateCourseDetail extends HttpServlet {
    private Repositary repo;

    @Override
    public void init() throws ServletException {
        super.init();
        this.repo = new Repositary();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isCourseUpdated = false;
        try {
            isCourseUpdated = repo.updateCourse(Integer.parseInt(request.getParameter("courseid")), request.getParameter("coursename"), request.getParameter("coursedescription"));
        } catch (Exception e) {
            System.out.println(e);
        }
        if (isCourseUpdated) {
            System.out.println("Course data updated");
            response.sendRedirect("/ServeletProject/admin");
        } else System.out.println("Course data not updated");
    }

    @Override
    public void destroy() {
        super.destroy();
        this.repo = null;
    }
}
