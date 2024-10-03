package com.example.tkt_module3.Controller;


import com.example.tkt_module3.Model.MatBangThue;
import com.example.tkt_module3.Service.IMatBangThueServer;
import com.example.tkt_module3.Service.MatBangThueService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "")

public class MatBangThueServlet extends HttpServlet {
    IMatBangThueServer iMatBangService = new MatBangThueService();

    public MatBangThueServlet() throws SQLException, ClassNotFoundException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request,response);
                break;
//
            case "delete":
                deleteMatBang(request, response);
                break;
            default:
                findAll(request,response);
        }

    }


    private void deleteMatBang(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String maMb = request.getParameter("sid");
        iMatBangService.deleteMatBang(maMb);
        response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {

            case "create":
//                addNewStudent(request,response);
                break;
            default:
                findAll(request,response);
        }
    }

    //    private void addNewStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = request.getParameter("name");
//        int gender = Integer.parseInt(request.getParameter("gender"));
//        String email = request.getParameter("email");
//        int classId = Integer.parseInt(request.getParameter("classId"));
//
//        if (regaxName(name)) {
//            request.setAttribute("errorMessage", "Tên không chứa số và kí tự đặc biệt.");
//            forwardCreateForm(request, response, name, gender, email, classId);
//            return;
//        }
//
//        iStudentService.addNewStudent(new MatBang(name, gender, email, new ClassName(classId)));
//        response.sendRedirect("/");
//    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MatBangThue> list = iMatBangService.findAll();
        request.setAttribute("listCr", list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        requestDispatcher.forward(request, response);
    }
//    private void forwardCreateForm(HttpServletRequest request, HttpServletResponse response, String name, int gender, String email, int classId) throws ServletException, IOException {
//        MatBang matBang = new MatBang(name, gender, email, new ClassName(classId));
//        request.setAttribute("students", matBang);
//        List<ClassName> classList = iStudentService.getAllClass();
//        request.setAttribute("listCr", classList);
//        request.getRequestDispatcher("create.jsp").forward(request, response);
//    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listMB", iMatBangService.findAll());
        request.getRequestDispatcher("list.jsp").forward(request,response);
    }

    private boolean regaxName(String name) {
        return !name.matches("^[\\p{L}\\s]{1,150}$");
    }

}