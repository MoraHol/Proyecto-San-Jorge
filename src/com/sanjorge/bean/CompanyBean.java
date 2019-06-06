/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.bean;

import com.sanjorge.model.Company;
import com.sanjorge.model.Offer;
import com.sanjorge.service.CompanyService;
import com.sanjorge.service.OfferService;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
    private OfferService offerService;
    private ArrayList<Offer> offers;
    @PostConstruct
    public void init(){
        companyService = new CompanyService();
        companies = companyService.list();
        offerService = new OfferService();
        try {
            offers = offerService.list((Company) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("company"));
        } catch (Exception e) {
        }
    }
    
    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }
    
   
}
