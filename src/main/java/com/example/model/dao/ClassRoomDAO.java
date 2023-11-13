package com.example.model.dao;

import com.example.model.entity.ClassRoom;
import com.example.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassRoomDAO implements IGennericDAO<ClassRoom,Integer>{
    @Override
    public List<ClassRoom> findAll() {
        Connection connection=null;
        List<ClassRoom> list =new ArrayList<>();
        connection= ConnectionDB.openConnection();
        try {
            PreparedStatement pstm=connection.prepareStatement("SELECT *FROM class");
           ResultSet rs= pstm.executeQuery();
           while (rs.next()){
               ClassRoom classRoom=new ClassRoom();
               classRoom.setId(rs.getInt("id"));
               classRoom.setName(rs.getString("name"));
               list.add(classRoom);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return list;
    }

    @Override
    public Boolean create(ClassRoom aClass) {
        return null;
    }

    @Override
    public Boolean update(ClassRoom aClass, Integer integer) {
        return null;
    }

    @Override
    public ClassRoom findId(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
