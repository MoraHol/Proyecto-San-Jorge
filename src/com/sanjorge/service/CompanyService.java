/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.service;

import com.sanjorge.dao.CompanyDaoImpl;
import com.sanjorge.idao.ICompanyDao;
import com.sanjorge.model.Company;

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
        return 0;
    }
    public int update(Company company){
        return 0;
    }
    public int delete(Company company){
        return 0;
    }
}
