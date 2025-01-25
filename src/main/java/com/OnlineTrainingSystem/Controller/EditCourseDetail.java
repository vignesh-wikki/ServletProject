package com.OnlineTrainingSystem.Controller;


import com.OnlineTrainingSystem.DAO.Repositary;
import com.OnlineTrainingSystem.Model.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/getCourseDetail/*")
public class EditCourseDetail extends HttpServlet {
    private Repositary repo;

    @Override
    public void init() throws ServletException {
        super.init();
        this.repo = new Repositary();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        boolean isCourseAvailabe = false;
        try {
            String[] parts = request.getPathInfo().substring(1).split("/");
            Course course = repo.getCourseDetail(Integer.parseInt(parts[0]));
            if (course != null) {
                HttpSession session = request.getSession();
                session.setAttribute("CourseDetails", course);
                isCourseAvailabe = true;
            }
            if (isCourseAvailabe) response.sendRedirect("/ServeletProject/editCourseDetail.jsp");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void destroy() {
        super.destroy();
        this.repo = null;
    }
}
