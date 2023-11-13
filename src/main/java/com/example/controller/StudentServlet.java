package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.model.entity.ClassRoom;
import com.example.model.entity.Student;
import com.example.model.service.ClassRoomService;
import com.example.model.service.StudentService;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "StudentServlet", value = "/studentservlet")
public class StudentServlet extends HttpServlet {
    StudentService studentService;
    ClassRoomService classRoomService;

    @Override
    public void init() {
        studentService=new StudentService();
        classRoomService=new ClassRoomService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");

        if(action == null){
            showListStudent(request, response);
        }else {
int studentId=0;
            switch (action){
                case "add":
                   addStudent(request,response);
                    break;
                case "edit":

                    studentId = Integer.parseInt(request.getParameter("id"));
                    StudentDTO studentDTO = studentService.findId(studentId);
                    request.setAttribute("studentDTO", studentDTO);
                    request.getRequestDispatcher("views/edit-student.jsp").forward(request,response);
                    break;
                case "delete":
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String dateStr = request.getParameter("birthday");
        Integer classId = Integer.parseInt(request.getParameter("classId"));
        // convert tu string => sql.date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        StudentDTO student = new StudentDTO();
        student.setName(name);
        student.setClassId(classId);
        student.setBirthday(new java.sql.Date(birthday.getTime()));
        studentService.create(student);
        showListStudent(request,response);
    }

    public void showListStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StudentDTO> listStudent = studentService.findAll();
        request.setAttribute("list", listStudent);
        request.getRequestDispatcher("views/student.jsp").forward(request, response);
    }

    public void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ClassRoom> list = classRoomService.findAll();
        request.setAttribute("list",list);
        request.getRequestDispatcher("views/add-student.jsp").forward(request,response);
    }



}