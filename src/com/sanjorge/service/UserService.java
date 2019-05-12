/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.service;

import com.sanjorge.dao.UserDaoImpl;
import com.sanjorge.idao.IUserDao;
import com.sanjorge.model.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author alexis
 */
public class UserService {

    private final IUserDao userDao;

    public UserService() {
        userDao = new UserDaoImpl();
    }

    public int insert(User user) {
        int result = -1;
        try {
            result = userDao.save(user);
        } catch (Exception e) {
            System.out.println("userService: Se presento un error al "
                    + "insertar: " + e.getMessage());
        }
        return result;
    }

    public int update(User user) {
        int result = -1;
        try {
            result = userDao.update(user);
        } catch (Exception e) {
            System.out.println("userService: Se presento un error al "
                    + "actualizar: " + e.getMessage());
        }
        return result;
    }

    public ArrayList<User> getall() throws Exception {

        ArrayList<User> list = new ArrayList<>();
        try {
            list = userDao.findAll();
        } catch (Exception e) {
            System.out.println("PersonaService: Se presento un error al "
                    + "listar la tabla: " + e.getMessage());
        }
        return list;
    }

    public User getUserById(int id) throws Exception {
        User user = new User();
        try {
            user = userDao.findUserById(id);
        } catch (Exception e) {
            System.out.println("PesonaService: Se presento un error al "
                    + "consultar por id en la tabla: " + e.getMessage());
        }
        return user;
    }

    public int deleteById(int id) throws Exception {
        int result = -1;
        try {
            result = userDao.delete(id);
        } catch (Exception e) {
            System.out.println("PersonaService: Se presento un error al "
                    + "consultar por id en la tabla: " + e.getMessage());
        }
        return result;

    }

    public User consultByEmail(String email) throws Exception {
        User user = new User();
        try {
            user = userDao.findByEmail(email);
        } catch (Exception e) {
            System.out.println("UsuarioService: Se presento un error al "
                    + "consultar por id en la tabla: " + e.getMessage());
        }
        return user;
    }

    public User authenticateUser(String email, String password) throws Exception {
        User usuario = consultByEmail(email);
        if (usuario != null) {
            if (usuario.getPassword().equals(convertSHA256(password))) {
                return usuario;
            } else {
                throw new Exception("Contraseña Incorrecta");
            }
        } else {
            throw new Exception("El Correo no se encuentra registrado");
        }
    }

    public String convertSHA256(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
