/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.idao;

import com.sanjorge.model.Category;
import java.util.ArrayList;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
public interface ICategoryDao {
    public ArrayList<Category> findAll();
    public Category findById(int id);
    public int save(Category category);
    public int delete(int id);
    public int update(Category category);
    
}
