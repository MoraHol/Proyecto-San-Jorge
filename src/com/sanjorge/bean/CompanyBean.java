/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.bean;

import com.sanjorge.model.Company;
import com.sanjorge.service.CompanyService;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
@ManagedBean(name = "companyBean")
@ViewScoped
public class CompanyBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Company> companies;
    private CompanyService companyService;
    
    @PostConstruct
    public void init(){
        companyService = new CompanyService();
        companies = companyService.list();
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }
    
   
}
