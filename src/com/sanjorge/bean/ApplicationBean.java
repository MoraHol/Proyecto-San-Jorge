/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.bean;

import com.sanjorge.dao.AplicationDaoImpl;
import com.sanjorge.dao.OfferDaoImpl;
import com.sanjorge.idao.IAplicationDao;
import com.sanjorge.idao.IOfferDao;
import com.sanjorge.model.Aplication;
import com.sanjorge.model.Offer;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alexis
 */
@ManagedBean(name = "ApplicationBean")
@ViewScoped
public class ApplicationBean {
    private IAplicationDao applicationDao;
    private Integer idOffer;
    private Offer offer;
    private ArrayList<Aplication> applications;
    private IOfferDao offerDao;
    /**
     * Creates a new instance of ApplicationBean
     */
    public ApplicationBean() {
        applicationDao = new AplicationDaoImpl();
        offerDao = new OfferDaoImpl();
    }

     public void preRenderView() {
        offer = offerDao.findOfferById(idOffer);
        applications = applicationDao.getApplicationsByOffer(offer);
    }
    
    public IAplicationDao getApplicationDao() {
        return applicationDao;
    }

    public void setApplicationDao(IAplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    public Integer getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(Integer idOffer) {
        this.idOffer = idOffer;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public ArrayList<Aplication> getApplications() {
        return applications;
    }

    public void setApplications(ArrayList<Aplication> applications) {
        this.applications = applications;
    }
    
}
