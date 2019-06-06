/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.service;

import com.sanjorge.dao.OfferDaoImpl;
import com.sanjorge.idao.IOfferDao;
import com.sanjorge.model.Company;
import com.sanjorge.model.Offer;
import java.util.ArrayList;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
public class OfferService {
    private IOfferDao offerDao;

    public OfferService() {
        this.offerDao = new OfferDaoImpl();
    }
    
    public ArrayList<Offer> list(Company company){
        ArrayList<Offer> offers = new ArrayList<>();
        try {
            offers = offerDao.listOffersByCompany(company);
        } catch (Exception e) {
        }
        return offers;
    }
    public ArrayList<Offer> list(){
        ArrayList<Offer> offers = new ArrayList<>();
        try {
            offers = offerDao.findAll();
        } catch (Exception e) {
        }
        return offers;
    }
    public Offer getById(int id){
        Offer offer = new Offer();
        try {
            offer = offerDao.findOfferById(id);
        } catch (Exception e) {
        }
        return offer;
    }
    public int update(Offer offer){
        int status = 0;
        try {
            status = offerDao.updateOffer(offer);
        } catch (Exception e) {
        }
        return status;
    }
    public int delete(int id){
        int status = 0;
        try {
            status = offerDao.deleteOffer(id);
        } catch (Exception e) {
        }
        return status;
    }
}
