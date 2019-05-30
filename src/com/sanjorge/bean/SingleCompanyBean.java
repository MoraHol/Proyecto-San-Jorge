/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.bean;

import com.sanjorge.model.Company;
import com.sanjorge.service.CompanyService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author Alexis Holguin github:Mora
 */
@ManagedBean(name= "singleCompanyBean")
@ViewScoped
public class SingleCompanyBean {
    private Integer idCompany;
    private Company company;
    private CompanyService companyService;
    /**
     * Creates a new instance of SingleCompanyBean
     */
    public SingleCompanyBean() {
        companyService = new CompanyService();
        company =  companyService.getById((getIdCompany()));
    }
    public void preRenderView(){
       company =  companyService.getById(getIdCompany());
       System.out.println(idCompany);
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    
}
