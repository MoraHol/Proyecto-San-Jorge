/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.dao;

import com.sanjorge.idao.IOfferDao;
import com.sanjorge.model.Company;
import com.sanjorge.model.Offer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Adrian Hoyos
 */
public class OfferDaoImpl extends ConnectionSQL implements IOfferDao {
    //Create, Read, Update, Delete

    /**
     * Function to create a new offer
     *
     * @param offer Recives offer object
     * @return if the offer inserts correctly
     */
    public int createOffer(Offer offer) {
        int status = 0;
        try {
            this.connect();
            String query = "INSERT INTO `job_offers` (`id_job_offer`, `category_category_id`, `companies_id_company`, `created_at`, `working_day`) VALUES (?, ?, CURRENT_TIMESTAMP, ?);";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);

            pstm.setInt(1, offer.getCategory().getId());
            pstm.setInt(2, offer.getCompany().getId());
            pstm.setString(3, offer.getWorking_day());
            status = pstm.executeUpdate();
            System.out.println(status);

            this.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public Offer findById(int id){
        Offer offer = new Offer();
        try {
            String query = "SELECT * \n" +
                           "FROM job_offers JO \n" +
                           "INNER JOIN category C\n" +
                           "ON JO.category_category_id = C.category_id\n" +
                           "INNER JOIN companies CO\n" +
                           "ON JO.companies_id_company = CO.id_company\n" +
                           "WHERE id_job_offer = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
             pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                int t = 1;
                offer.setId(rs.getInt(t++));
            }
        } catch (Exception e) {
        }
        return offer;
    }
    
    /**
     * Function to bring offers in the database
     * @param company 
     * @return the offers of the specified company
     */
    public ArrayList<Offer> listOffersByCompany(Company company) {
        ArrayList<Offer> offers = new ArrayList<>();
        try {
            String query = "SELECT * FROM `job_offers` WHERE `companies_id_company` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, company.getId());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                 offers.add(this.findById(rs.getInt("id_job_offer")));
            }
        } catch (Exception e) {
        }
        return offers;
    }

}
