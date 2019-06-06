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
import com.sanjorge.model.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
@ManagedBean(name = "ApplicationBean")
@ViewScoped
public class ApplicationBean implements Serializable{

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

    public ArrayList<Aplication> getApplicationsByUser() {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        return applicationDao.findAplicationByUser(user.getId()); 
    }

    public void preRenderView() {
        offer = offerDao.findOfferById(idOffer);
        applications = applicationDao.findApplicationsByOffer(offer);
        System.out.println(applications.toString());
    }
    
    public void delete(int id){
        if(applicationDao.delete(id) > 0){
            FacesContext.getCurrentInstance().addMessage("messagesApp", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se ha borrado la aplicación"));
        }else{
            FacesContext.getCurrentInstance().addMessage("messagesApp", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se ha borrado la aplicación"));
        }
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
