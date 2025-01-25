package com.OnlineTrainingSystem.Controller;

import com.OnlineTrainingSystem.DAO.Repositary;
import com.OnlineTrainingSystem.Model.Student;
import com.OnlineTrainingSystem.Model.UserCreditionals;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

@WebServlet("/getUserDetail/*")
public class EditUserDetail extends HttpServlet {
    private Repositary repo;

    @Override
    public void init() throws ServletException {
        super.init();
        this.repo = new Repositary();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        boolean isUserAvailabe = false;
        try {
            String[] parts = request.getPathInfo().substring(1).split("/");
            Map<UserCreditionals, Student> map = repo.getEditUserDetail(Integer.parseInt(parts[0]));
            if (!map.isEmpty()) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", Integer.parseInt(parts[0]));
                session.setAttribute("userDetails", map);
                isUserAvailabe = true;
            }
            if (isUserAvailabe) response.sendRedirect("/ServeletProject/editUserDetail.jsp");

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
