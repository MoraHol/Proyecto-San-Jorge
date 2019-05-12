/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.idao;

import com.sanjorge.model.User;
import java.util.ArrayList;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
public interface IUserDao {
    public ArrayList<User> findAll();
    public User findUserById(int id);
    public int save(User user);
    public int delete(int id);
    public int update(User user);
    public User findByEmail(String email);

}
