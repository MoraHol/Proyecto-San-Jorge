/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.bean;

import com.sanjorge.dao.AplicationDaoImpl;
import com.sanjorge.idao.IAplicationDao;
import com.sanjorge.model.Aplication;
import com.sanjorge.model.Offer;
import com.sanjorge.model.User;
import com.sanjorge.service.OfferService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
@ManagedBean(name = "offerBean")
@ViewScoped
public class OfferBean implements Serializable{

    private Integer idOffer;
    private Offer offer;
    private OfferService offerService;
    private IAplicationDao applicationDao;
    /**
     * Creates a new instance of OfferBean
     */
    public OfferBean() {
        offerService = new OfferService();
        applicationDao = new AplicationDaoImpl();
        offer = new Offer();
    }

    public void preRenderView() {
        offer = offerService.getById(idOffer);
        System.out.println(offer.getTitle());
        System.out.println(idOffer);
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

    public OfferService getOfferService() {
        return offerService;
    }

    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    public void update() {
        if (offerService.update(this.offer) > 0) {
            FacesContext.getCurrentInstance().addMessage("messagesApp", new FacesMessage(FacesMessage.SEVERITY_INFO, "exito", "Se ha actualizado la oferta"));
        } else {
            FacesContext.getCurrentInstance().addMessage("messagesApp", new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se ha actualizado la oferta"));
        }
    }

    public void delete() {
        try {
            if (offerService.delete(offer.getId()) > 0) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("list_offers.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage("messagesApp", new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se ha borrado la oferta"));
            }
        } catch (Exception e) {

        }
    }
    public void apply(){
        Aplication application = new Aplication();
        try {
            application.setOffer(offer);
            application.setUser((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
            if(applicationDao.save(application) > 0){
                FacesContext.getCurrentInstance().addMessage("messagesApp", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Has aplicado a la oferta"));
            }else{
                FacesContext.getCurrentInstance().addMessage("messagesApp", new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ya aplicaste a esta oferta"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesApp", new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", e.getMessage()));
        }
    }
}
