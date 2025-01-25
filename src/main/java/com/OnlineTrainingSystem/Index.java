package com.OnlineTrainingSystem;

import com.OnlineTrainingSystem.DAO.Repositary;
import com.OnlineTrainingSystem.Model.Course;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class Index extends HttpServlet {
    private Repositary repo;
    private Course getAllCourse;
    @Override
    public void init() throws ServletException {
        super.init();
        this.repo = new Repositary();
        this.getAllCourse = new Course();
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//        System.out.println("Home called");
//        Cookie[] cookie = req.getCookies();
//        for(Cookie cookies : cookie){
//            if(cookies.getName().equals("result")){
//                cookies.setAttribute("result",cookies.getValue());
//            }
//        }
        HttpSession session = req.getSession();
        if (Boolean.TRUE.equals(session.getAttribute("isLogedin"))) {
            try {
                repo.setNewCourse(getAllCourse);
                List<Course> list = repo.getAllCources();
                req.setAttribute("courseList", list);
                RequestDispatcher dispatcher = req.getRequestDispatcher("course.jsp");
                dispatcher.forward(req, res);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
        } else {
            res.sendRedirect("login.jsp");
        }

    }
    @Override
    public void destroy(){
        super.destroy();
        this.repo = null;
        this.getAllCourse = null;
    }
}
