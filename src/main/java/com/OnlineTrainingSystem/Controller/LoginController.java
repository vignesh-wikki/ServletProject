package com.OnlineTrainingSystem.Controller;

import com.OnlineTrainingSystem.DAO.Repositary;
import com.OnlineTrainingSystem.Model.Course;
import com.OnlineTrainingSystem.Model.UserCreditionals;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private Repositary repo;
    private UserCreditionals user;
    private Course CouresList;

    @Override
    public void init() throws ServletException {
        super.init();
        this.repo = new Repositary();
        this.user = new UserCreditionals();
        this.CouresList = new Course();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            if ("admin@gamil.com".equals(request.getParameter("useremail")) && "7".equals(request.getParameter("userpassword"))) {
                HttpSession session = request.getSession();
                session.setAttribute("isAdmin", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin");
                dispatcher.forward(request, response);
            } else {
                user.setUserEmail(request.getParameter("useremail"));
                user.setUserPassword(request.getParameter("userpassword"));
                repo.setUser(user);
                boolean isLogined = repo.requestLogging();
                if (isLogined) {
                    HttpSession session = request.getSession();
                    session.setAttribute("isLogedin", true);
                    session.setAttribute("userEmail", user.getUserEmail());

                    repo.setNewCourse(CouresList);
                    List<Course> list = repo.getAllCources();
                    request.setAttribute("courseList", list);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("course.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Username or password is Invalid");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                    dispatcher.forward(request, response);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        this.repo = null;
        this.user = null;
        this.CouresList = null;
    }

}
