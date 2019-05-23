/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.service;

import com.sanjorge.dao.CompanyDaoImpl;
import com.sanjorge.idao.ICompanyDao;
import com.sanjorge.model.Company;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
public class CompanyService {
    private ICompanyDao companyDao;
    
    public CompanyService(){
        companyDao = new CompanyDaoImpl();
    }
    public int insert(Company company){
        int result = 0;
        try {
            result = companyDao.save(company);
        } catch (Exception e) {
        }
        return result;
    }
    public int update(Company company){
        return 0;
    }
    public int delete(Company company){
        return 0;
    }
    public ArrayList<Company> list(){
        ArrayList<Company> list = new ArrayList<>();
        try {
            list = companyDao.findAll();
        } catch (Exception e) {
        }
        return list;
        
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
