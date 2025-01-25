package com.OnlineTrainingSystem.Controller;

import com.OnlineTrainingSystem.DAO.Repositary;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteUser/*")
public class DeleteUser extends HttpServlet {
    Repositary repo;

    public void init() throws ServletException {
        super.init();
        repo = new Repositary();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isUserDeleted = false;
        try {
            String[] parts = request.getPathInfo().substring(1).split("/");
            isUserDeleted = repo.deleteUser(Integer.parseInt(parts[0]));
        } catch (Exception e) {
            System.out.println(e);
        }

        if (isUserDeleted) response.sendRedirect("/ServeletProject/admin");
        else System.out.println("User deleted");
    }

    @Override
    public void destroy() {
        super.destroy();
        repo = null;
    }
}
