package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.model.entity.ClassRoom;
import com.example.model.entity.Student;
import com.example.model.service.ClassRoomService;
import com.example.model.service.StudentService;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ClassServlet", value = "/ClassServlet")
public class ClassServlet extends HttpServlet {
    ClassRoomService classRoomService;
    StudentService studentService;
    @Override
    public void init() {
        classRoomService=new ClassRoomService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        showListClassRoom(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
    public void showListClassRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ClassRoom> list = classRoomService.findAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("views/class-rom.jsp").forward(request, response);
    }
    public void showListStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StudentDTO> list = studentService.findAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("views/class-rom.jsp").forward(request, response);
    }
}