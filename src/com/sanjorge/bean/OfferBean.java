/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.bean;

import com.sanjorge.model.Offer;
import com.sanjorge.service.OfferService;
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
public class OfferBean {

    private Integer idOffer;
    private Offer offer;
    private OfferService offerService;

    /**
     * Creates a new instance of OfferBean
     */
    public OfferBean() {
        offerService = new OfferService();
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
}
