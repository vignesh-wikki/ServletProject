package com.OnlineTrainingSystem.Controller;

import com.OnlineTrainingSystem.DAO.Repositary;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/updateUserdetail")
public class UpdateUserdetail extends HttpServlet {
    Repositary repo;

    @Override
    public void init() throws ServletException {
        super.init();
        repo = new Repositary();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isUserdetailUpdated = false;
        try {
            HttpSession session = request.getSession();
            int id = (int) session.getAttribute("userId");
            isUserdetailUpdated = repo.updateUser(request.getParameter("username"), request.getParameter("userdepartment"), request.getParameter("useremail"), id);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (isUserdetailUpdated) {
            System.out.println("Student data updated");
            response.sendRedirect("/ServeletProject/admin");
        } else System.out.println("Student data not updated");
    }

    @Override
    public void destroy() {
        super.destroy();
        repo = null;
    }
}
