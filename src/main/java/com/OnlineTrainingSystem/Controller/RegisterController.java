package com.OnlineTrainingSystem.Controller;

import com.OnlineTrainingSystem.DAO.Repositary;
import com.OnlineTrainingSystem.Model.Student;
import com.OnlineTrainingSystem.Model.UserCreditionals;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private Repositary repo;
    private UserCreditionals user;
    private Student newStudent;

    @Override
    public void init() throws ServletException {
        super.init();
        this.repo = new Repositary();
        this.user = new UserCreditionals();
        this.newStudent = new Student();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            user.setUserEmail(request.getParameter("useremail"));
            user.setUserPassword(request.getParameter("userpassword"));
            newStudent.setName(request.getParameter("username"));
            newStudent.setDepartment(request.getParameter("userdepartment"));
            repo.setNewStudent(newStudent);
            repo.setUser(user);
            boolean isRegisterdeSuccess = repo.requestRegistering();
            if (isRegisterdeSuccess) {
                System.out.println("Redirecting to login");
                response.sendRedirect("login.jsp");
            } else {
                request.setAttribute("error", "User already exists!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
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
        this.user = null;
        this.newStudent = null;
    }
}

