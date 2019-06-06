/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.bean;

import com.sanjorge.model.Offer;
import com.sanjorge.service.OfferService;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
@ManagedBean
@ViewScoped
public class OffersBean implements Serializable{
    private OfferService offerService;
    /**
     * Creates a new instance of OffersBean
     */
    public OffersBean() {
        offerService = new OfferService();
    }
    public ArrayList<Offer> getAllOffers(){
        return offerService.list();
    }
    
}
