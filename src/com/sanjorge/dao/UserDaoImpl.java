/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.dao;

import com.sanjorge.idao.IUserDao;
import com.sanjorge.model.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author alexis
 */
public class UserDaoImpl extends ConnectionSQL implements IUserDao {

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> list = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT * FROM `users`";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(this.findUserById(rs.getInt(1)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public User findUserById(int id) {
        User user = new User();
        try {
            this.connect();
            String query = "SELECT * FROM `users` WHERE `id_user` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int t = 1;
                user.setId(rs.getInt(t++));
                user.setFirstName(rs.getString(t++));
                user.setSecondName(rs.getString(t++));
                user.setFirstSurName(rs.getString(t++));
                user.setSecondSurName(rs.getString(t++));
                user.setPassword(rs.getString(t++));
                user.setEmail(rs.getString(t++));
                user.setIdentificationNumber(rs.getInt(t++));
                user.setGender(rs.getString(t++));
                user.setBirthdate(rs.getDate(t++));
                user.setCivilStatus(rs.getString(t++));
                user.setAddress(rs.getString(t++));
                user.setPhoneNumber(rs.getString(t++));
                user.setPhoto(rs.getBlob(14).getBytes(1, (int) rs.getBlob(14).length()));
                user.setProfile(rs.getString(t++));
            }
        } catch (Exception e) {
        }
        return user;
    }

    @Override
    public int save(User user) {
        int status = 0;
        try {
            this.connect();
            String query = "INSERT INTO `users` (`first_name`, `second_name`, `first_surname`, `second_surname`, `password`, `email`, `indentification_number`, `gender`, `birthdate`, `civil_status`, `address`, `phone_number`, `photo`, `profile`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setString(1, user.getFirstName());
            pstm.setString(2, user.getSecondName());
            pstm.setString(3, user.getFirstSurName());
            pstm.setString(4, user.getSecondSurName());
            pstm.setString(5, user.getPassword());
            pstm.setString(6, user.getEmail());
            pstm.setInt(7, user.getIdentificationNumber());
            pstm.setString(8, user.getGender());
            pstm.setDate(9, new java.sql.Date(user.getBirthdate().getTime()));
            pstm.setString(10, user.getCivilStatus());
            pstm.setString(11, user.getAddress());
            pstm.setString(12, user.getPhoneNumber());
            pstm.setBlob(13, new SerialBlob(user.getPhoto()));
            pstm.setString(14, user.getProfile());
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("UserDao Eror al insertar: " + e.getMessage());
        }
        return status;
    }

    @Override
    public int delete(int id) {
        int status = 0;
        try {
            this.connect();
            String query = "DELETE FROM `users` WHERE `users`.`id_user` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("UserDao:" + e.getMessage());
        }
        return status;
    }

    @Override
    public int update(User user) {
        int status = 0;
        try {
            this.connect();
            String query = "UPDATE `users` SET `first_name` = ?, `second_name` = ?, `first_surname` = ?, `second_surname` = ?, `password` = ?, `email` = ?, `indentification_number` =? , `gender` = ?, `birthdate` = ?, `civil_status` = ?, `address` = ?, `phone_number` = ?, `photo` = ?, `profile` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setString(1, user.getFirstName());
            pstm.setString(2, user.getSecondName());
            pstm.setString(3, user.getFirstSurName());
            pstm.setString(4, user.getSecondSurName());
            pstm.setString(5, user.getPassword());
            pstm.setString(6, user.getEmail());
            pstm.setInt(7, user.getIdentificationNumber());
            pstm.setString(8, user.getGender());
            pstm.setDate(9, (Date) user.getBirthdate());
            pstm.setString(10, user.getCivilStatus());
            pstm.setString(11, user.getAddress());
            pstm.setString(12, user.getPhoneNumber());
            pstm.setBlob(13, new SerialBlob(user.getPhoto()));
            pstm.setString(14, user.getProfile());
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("UserDao:" + e.getMessage());
        }
        return status;
    }

    @Override
    public User findByEmail(String email) {
        User user = new User();
        try {
            this.connect();
            String query = "SELECT id_user FROM `users` WHERE `users`.`email` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                user = this.findUserById(rs.getInt(1));
            }
        } catch (Exception e) {
            System.err.println("UserDao:" + e.getMessage());
        }
        return user;
    }

}
