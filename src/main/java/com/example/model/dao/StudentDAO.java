package com.example.model.dao;

import com.example.dto.StudentDTO;
import com.example.model.entity.ClassRoom;
import com.example.model.entity.Student;
import com.example.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IGennericDAO<StudentDTO,Integer> {
    @Override
    public List<StudentDTO> findAll() {
        Connection connection=null;
        List<StudentDTO> studentList =new ArrayList<>();
        connection= ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement=connection.prepareCall("CALL PROC_GETALLSTUDENT");
        ResultSet rs=callableStatement.executeQuery();
        while (rs.next()){
            StudentDTO studentDTO=new StudentDTO();
            studentDTO.setId(rs.getInt("id"));
            studentDTO.setName(rs.getString("student_name"));
            studentDTO.setBirthday(rs.getDate("birthday"));
            studentDTO.setClassId(rs.getInt("class_id"));
            studentDTO.setClassName(rs.getString("className"));
            studentList.add(studentDTO);
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }

        return studentList;
    }

    @Override
    public Boolean create(StudentDTO studentDTO) {
        Connection connection = null;

        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_INSERT_STUDENT(?,?,?)}");
            callableStatement.setString(1,studentDTO.getName());
            callableStatement.setDate(2,studentDTO.getBirthday());
            callableStatement.setInt(3,studentDTO.getClassId());
            int check = callableStatement.executeUpdate();
            if(check >0)
                return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Boolean update(StudentDTO studentDTO, Integer id) {
        Connection connection = ConnectionDB.openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall("{call UPDATE_STUDENT(?, ? , ?, ?)}");
            callableStatement.setInt(1, id);
            callableStatement.setString(2, studentDTO.getName());
            callableStatement.setDate(3, studentDTO.getBirthday());
            callableStatement.setInt(4, studentDTO.getClassId());
            int check = callableStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public StudentDTO findId(Integer id) {
        StudentDTO studentDTO = new StudentDTO();
        Connection connection = ConnectionDB.openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall("{call (?)}");
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                studentDTO.setId(resultSet.getInt("Student ID"));
                studentDTO.setName(resultSet.getString("Name"));
                studentDTO.setBirthday(resultSet.getDate("Birthday"));
                studentDTO.setClassId(resultSet.getInt("Class ID"));
                studentDTO.setClassName(resultSet.getString("Class"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        }
        return studentDTO;

    }

    @Override
    public void delete(Integer id) {

    }
}
