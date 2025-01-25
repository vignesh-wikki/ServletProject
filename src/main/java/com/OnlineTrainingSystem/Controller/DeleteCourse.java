package com.OnlineTrainingSystem.Controller;

import com.OnlineTrainingSystem.DAO.Repositary;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteCourse/*")
public class DeleteCourse extends HttpServlet {
    Repositary repo;

    public void init() throws ServletException {
        super.init();
        repo = new Repositary();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        boolean isCourseDeleted = false;
        try {
            String[] parts = request.getPathInfo().substring(1).split("/");
            isCourseDeleted = repo.deleteCourse(Integer.parseInt(parts[0]));
            response.sendRedirect("/ServeletProject/admin");
        } catch (Exception e) {
            System.out.println(e);
        }
        if (!isCourseDeleted) System.out.println("Course not deleted");
        else System.out.println("Course deleted");
    }

    @Override
    public void destroy() {
        super.destroy();
        repo = null;
    }
}
