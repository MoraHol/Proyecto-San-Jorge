package com.sanjorge.dao;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.sanjorge.idao.IAplicationDao;
import com.sanjorge.idao.IOfferDao;
import com.sanjorge.idao.IUserDao;
import com.sanjorge.model.Aplication;
import com.sanjorge.model.Offer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author David Viuche, Alexis Holguin github:MoraHol
 */
public class AplicationDaoImpl extends ConnectionSQL implements IAplicationDao {
    private IOfferDao offerDao;
    private IUserDao userDao;
    
    public AplicationDaoImpl(){
        offerDao = new OfferDaoImpl();
        userDao = new UserDaoImpl();
    }
    
    @Override
    public ArrayList<Aplication> findAll() {
        ArrayList<Aplication> list = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT * FROM `applications`";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int t = 1;
                Aplication aplication = new Aplication();
                aplication.setId(rs.getInt(t++));
                aplication.setOffer(offerDao.findOfferById(rs.getInt(t++)));
                aplication.setUser(userDao.findUserById(rs.getInt(t++)));
                aplication.setCreated_at(rs.getDate(t++));
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public ArrayList<Aplication> findAplicationByUser(int id) {
        ArrayList<Aplication> applications = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT * FROM `applications` WHERE `users_id_user` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
                int t = 1;
                Aplication application = new Aplication();
                application.setId(rs.getInt(t++));
                application.setUser(userDao.findUserById(rs.getInt(t++)));
                application.setOffer(offerDao.findOfferById(rs.getInt(t++)));
                application.setCreated_at(rs.getDate(t++));
                applications.add(application);
            }
        } catch (Exception e) {
        }
        return applications;
    }

    @Override
    public int save(Aplication aplication) throws Exception{
        int status = 0;
        try {
            this.connect();
            String query = "INSERT INTO `applications` (`users_id_user`, `job_offers_id_job_offer`) VALUES (?, ?)";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, aplication.getUser().getId());
            pstm.setInt(2, aplication.getOffer().getId());
            

            status = pstm.executeUpdate();
            this.disconnect();
        }catch(MySQLIntegrityConstraintViolationException ex){
            throw new Exception("Ya aplicaste a esta oferta");
        } catch (Exception e) {
            System.err.println("AplicationDao: " + e.getMessage());
        }
        return status;
    }

    @Override
    public int delete(int id) {
        int status = 0;
        try {
            this.connect();
            String query = "DELETE FROM `applications` WHERE `applications`.`id_application` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("AplicationDao:" + e.getMessage());
        }
        return status;
    }

    @Override
    public int update(Aplication aplication) {
        int status = 0;
        try {
            this.connect();
            String query = "UPDATE `applications` SET `users_id_user` = ?, `job_offers_id_job_offer` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, aplication.getOffer().getId());
            pstm.setInt(2, aplication.getUser().getId());
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("AplicationDao:" + e.getMessage());
        }
        return status;
    }

    @Override
    public ArrayList<Aplication> findApplicationsByOffer(Offer offer) {
        ArrayList<Aplication> applications = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT * FROM `applications` WHERE job_offers_id_job_offer = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, offer.getId());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int t = 1;
                Aplication application = new Aplication();
                application.setId(rs.getInt(t++));
                application.setUser(userDao.findUserById(rs.getInt(t++)));
                application.setOffer(offerDao.findOfferById(rs.getInt(t++)));
                application.setCreated_at(rs.getDate(t++));
                applications.add(application);
            }
            this.disconnect();
        } catch (Exception e) {
            System.err.println("AplicationDao:" + e.getMessage());
        }
        return applications;
    }
}
