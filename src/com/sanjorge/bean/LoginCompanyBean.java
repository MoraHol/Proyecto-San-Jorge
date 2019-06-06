/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.bean;

import com.sanjorge.model.Company;
import com.sanjorge.service.CompanyService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
@ManagedBean(name= "loginCompanyBean")
@SessionScoped
public class LoginCompanyBean {

    private String email;
    private Company company;
    private String password;
    private CompanyService companyService;
    
    
    /**
     * Creates a new instance of LoginCompanyBean
     */
    @PostConstruct
    public void init() {
        email = new String();
        password = new String();
        companyService = new CompanyService();
    }

    public boolean isAuthenticated() throws Exception {
        System.out.println(email+" " + password);
        company = companyService.authenticateCompany(email, password);
        return company != null;
    }
    
    public void verifySession(){
        Company company = (Company) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("company");
        if(company == null){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Proyecto-San-Jorge/loginCompany.xhtml");
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Por favor inicia Sesión"));
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void doLogin() throws Exception {
        try {
            if (isAuthenticated()) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("company", company);
                FacesContext.getCurrentInstance().getExternalContext().redirect("pages/company/dashboard.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesApp",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
    
}
