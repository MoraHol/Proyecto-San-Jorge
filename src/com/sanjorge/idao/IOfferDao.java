/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.idao;

import com.sanjorge.model.Company;
import com.sanjorge.model.Offer;
import java.util.ArrayList;

/**
 *
 * @author Adrian Hoyos
 */
public interface IOfferDao {
    public int createOffer(Offer offer);
    public Offer findOfferById(int id);
    public ArrayList<Offer> listOffersByCompany(Company company);
    public int deleteOffer(int id);
    public int updateOffer(Offer offer);
    public ArrayList<Offer> findAll();
}
