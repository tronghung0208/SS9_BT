package com.example.model.service;

import com.example.dto.StudentDTO;
import com.example.model.dao.StudentDAO;
import com.example.model.entity.Student;

import java.util.List;

public class StudentService implements IGenericService<StudentDTO,Integer> {
    StudentDAO studentDAO=new StudentDAO();
    @Override
    public List<StudentDTO> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public Boolean create(StudentDTO studentDTO) {
        return studentDAO.create(studentDTO);
    }

    @Override
    public Boolean update(StudentDTO studentDTO, Integer id) {
        return studentDAO.update(studentDTO,id);
    }

    @Override
    public StudentDTO findId(Integer id) {
        return studentDAO.findId(id);
    }

    @Override
    public void delete(Integer id) {

    }
}
