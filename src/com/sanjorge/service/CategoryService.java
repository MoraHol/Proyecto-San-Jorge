/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.service;

import com.sanjorge.dao.CategoryDaoImpl;
import com.sanjorge.idao.ICategoryDao;
import com.sanjorge.model.Category;
import java.util.ArrayList;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
public class CategoryService {

    private ICategoryDao categoryDao;

    public CategoryService() {
        categoryDao = new CategoryDaoImpl();
    }

    public ArrayList<Category> list() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            categories = categoryDao.findAll();
        } catch (Exception e) {
        }
        return categories;
    }

    public Category getCategoryById(int id) {
        Category category = new Category();
        try {
            category = categoryDao.findById(id);
        } catch (Exception e) {
        }
        return category;
    }
}
