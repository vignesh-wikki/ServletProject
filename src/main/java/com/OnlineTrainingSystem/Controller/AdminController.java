package com.OnlineTrainingSystem.Controller;

import com.OnlineTrainingSystem.DAO.Repositary;
import com.OnlineTrainingSystem.Model.Course;
import com.OnlineTrainingSystem.Model.Student;
import com.OnlineTrainingSystem.Model.UserCreditionals;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    private Repositary repo;

    public void init() throws ServletException {
        super.init();
        this.repo = new Repositary();
    }

    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<List<UserCreditionals>, List<Student>> map = repo.getAllUsers();
            repo.setNewCourse(new Course());
            List<Course> courses = repo.getAllCources();
            request.setAttribute("allUsers", map);
            request.setAttribute("courses", courses);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
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
