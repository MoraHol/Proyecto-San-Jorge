/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.dao;

import com.sanjorge.idao.IOfferDao;
import com.sanjorge.model.Category;
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

    public int createOffer(Offer offer) {
        int status = 0;
        try {
            this.connect();
            String query = "INSERT INTO `job_offers` (`id_job_offer`, `category_category_id`, `companies_id_company`, `created_at`, `working_day`, `title`, `description`, `requirements`, `salary`, `vacancy_numbers`) VALUES (NULL, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);

            pstm.setInt(1, offer.getCategory().getId());
            pstm.setInt(2, offer.getCompany().getId());
            pstm.setString(3, offer.getWorking_day());
            pstm.setString(4, offer.getTitle());
            pstm.setString(5, offer.getDescription());
            pstm.setString(6, offer.getRequirements());
            pstm.setLong(7, offer.getSalary());
            pstm.setInt(8, offer.getNumberVacants());
            status = pstm.executeUpdate();
            System.out.println(status);

            this.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }
    
    public Offer findOfferById(int id){
        Offer offer = new Offer();
        try {
            String query = "SELECT * FROM job_offers WHERE id_job_offer = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            CompanyDaoImpl companyOfTheOffer = new CompanyDaoImpl();//To use the function findCompanyById
            if(rs.next()){
                int t = 1;
                offer.setId(rs.getInt(t++));
                offer.setCompany(companyOfTheOffer.findCompanyById(rs.getInt(t++)));
                offer.setCategory(findCategoryById(rs.getInt(t++)));
                offer.setCreatedAt(rs.getDate(t++));
                offer.setWorking_day(rs.getString(t++));
                offer.setTitle(rs.getString(t++));
                offer.setDescription(rs.getString(t++));
                offer.setRequirements(rs.getString(t++));
                offer.setSalary(rs.getLong(t++));
                offer.setNumberVacants(t++);
            }
        } catch (Exception e) {
        }
        return offer;
    }
    
    public ArrayList<Offer> listOffersByCompany(Company company) {
        ArrayList<Offer> offers = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT id_job_offer FROM `job_offers` WHERE `companies_id_company` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, company.getId());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                 offers.add(this.findOfferById(rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println("OfferDao:" + e.getMessage());
        }
        return offers;
    }

    public int deleteOffer(int id){
        int status = 0;
        try {
            this.connect();
            String query = "DELETE FROM `job_offers` WHERE `job_offers`.`id` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
        }
        return status;
    }
    
    public int updateOffer(Offer offer){
        int status = 0;
        try {
            this.connect();
            String query = "UPDATE `job_offers` "
                    + "SET `category_category_id` = ?, `companies_id_company` = ?, `working_day` = ?"
                    + "WHERE `job_offers`.`id_job_offer` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, offer.getCategory().getId());
            pstm.setInt(2, offer.getCompany().getId());
            pstm.setString(3, offer.getWorking_day());
            pstm.setInt(4, offer.getId());
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("Offer Dao:" + e.getMessage());
        }
        return status;
    }

    private Category findCategoryById(int id) {
        Category categoryOfTheOffer = new Category();
        try {
            this.connect();
            String query = "SELECT * FROM `category` WHERE `category_id` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int t = 1;
                categoryOfTheOffer.setId(rs.getInt(t++));
                categoryOfTheOffer.setName(rs.getString(t++));
            }
        } catch (Exception e) {
        }
        return categoryOfTheOffer;
    }

}
