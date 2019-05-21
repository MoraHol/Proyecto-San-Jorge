package com.sanjorge.dao;

import com.sanjorge.idao.IAplicationDao;
import com.sanjorge.model.Aplication;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author David Viuche
 */
public class AplicationDaoImpl extends ConnectionSQL implements IAplicationDao {

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
                aplication.setId_offer(rs.getInt(t++));
                aplication.setId_user(rs.getInt(t++));
                
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public Aplication findAplicationByUser(int id) {
        Aplication aplication = new Aplication();
        try {
            this.connect();
            String query = "SELECT * FROM `applications` WHERE `users_id_user` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int t = 1;
                aplication.setId_offer(rs.getInt(t++));
                aplication.setId_user(rs.getInt(t++));
                
            }
        } catch (Exception e) {
        }
        return aplication;
    }

    @Override
    public int save(Aplication aplication) {
        int status = 0;
        try {
            this.connect();
            String query = "INSERT INTO `applications` (`users_id_user`, `job_offers_id_job_offer`) VALUES (?, ?)";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, aplication.getId_offer());
            pstm.setInt(2, aplication.getId_user());
     
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("AplicationDao:" + e.getMessage());
        }
        return status;
    }

    @Override
    public int delete(int id) {
        int status = 0;
        try {
            this.connect();
            String query = "DELETE FROM `applications` WHERE `applications`.`users_id_user` = ?";
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
            pstm.setInt(1, aplication.getId_offer());
            pstm.setInt(2, aplication.getId_user());
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("AplicationDao:" + e.getMessage());
        }
        return status;
    }

   
}