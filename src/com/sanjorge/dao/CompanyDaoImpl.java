package com.sanjorge.dao;

import com.sanjorge.idao.ICompanyDao;
import com.sanjorge.model.Company;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author David Viuche
 */
public class CompanyDaoImpl extends ConnectionSQL implements ICompanyDao {

    @Override
    public ArrayList<Company> findAll() {
        ArrayList<Company> list = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT * FROM `companies`";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int t = 1;
                Company company = new Company();
                company.setId(rs.getInt(t++));
                company.setName(rs.getString(t++));
                company.setEmail(rs.getString(t++));
                company.setPassword(rs.getString(t++));
                company.setDescription(rs.getString(t++));
                company.setWebpage(rs.getString(t++));
                company.setPhoneNumber(rs.getString(t++));
                company.setNit(rs.getString(t++));
                company.setAddress(rs.getString(t++));
                company.setLogo(rs.getBlob(t).getBytes(1, (int) rs.getBlob(t).length()));
                list.add(company);
            }
        } catch (Exception e) {
            System.out.println("CompanyDao: Error" + e.getMessage());
        }
        return list;
    }
    
    @Override
    public Company findCompanyById(int id) {
        Company company = new Company();
        try {
            this.connect();
            String query = "SELECT * FROM `company` WHERE `id_company` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int t = 1;
                company.setId(rs.getInt(t++));
                company.setName(rs.getString(t++));
                company.setEmail(rs.getString(t++));
                company.setPassword(rs.getString(t++));
                company.setEmail(rs.getString(t++));
                company.setDescription(rs.getString(t++));
                company.setWebpage(rs.getString(t++));
                company.setPhoneNumber(rs.getString(t++));
                company.setNit(rs.getString(t++));
                company.setAddress(rs.getString(t++));
                company.setLogo(rs.getBlob(t).getBytes(1, (int) rs.getBlob(t).length()));
            }
        } catch (Exception e) {
            System.out.println("CompanyDao: Error" + e.getMessage());
        }
        return company;
    }

    @Override
    public int save(Company company) {
        int status = 0;
        try {
            this.connect();
            String query = "INSERT INTO `companies` (`id_company`, `name`, `email`, `password`, `description`, `webpage`, `phone_number`, `nit`, `address`, `indentification_number`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setString(1, company.getName());
            pstm.setString(2, company.getEmail());
            pstm.setString(3, company.getPassword());
            pstm.setString(4, company.getDescription());
            pstm.setString(5, company.getWebpage());
            pstm.setString(6, company.getPhoneNumber());
            pstm.setString(7, company.getNit());
            pstm.setString(8, company.getAddress());
            pstm.setBlob(9, new SerialBlob(company.getLogo()));
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("CompanyDao:" + e.getMessage());
        }
        return status;
    }

    @Override
    public int delete(int id) {
        int status = 0;
        try {
            this.connect();
            String query = "DELETE FROM `companies` WHERE `companies`.`id_company` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("CompanyDao:" + e.getMessage());
        }
        return status;
    }

    @Override
    public int update(Company company) {
        int status = 0;
        try {
            this.connect();
            String query = "UPDATE `companies` SET `name` = ?, `email` = ?, `password` = ?, `description` = ?, `webpage` = ?, `email` = ?, `phone_number` =? , `nit` = ?, `address` = ?, `indentification_number` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setString(1, company.getName());
            pstm.setString(2, company.getEmail());
            pstm.setString(3, company.getPassword());
            pstm.setString(4, company.getDescription());
            pstm.setString(5, company.getWebpage());
            pstm.setString(6, company.getPhoneNumber());
            pstm.setString(7, company.getNit());
            pstm.setString(8, company.getAddress());
            pstm.setBlob(9, new SerialBlob(company.getLogo()));
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.out.println("CompanyDao: Error" + e.getMessage());
        }
        return status;
    }

    @Override
    public Company findByEmail(String email) {
        Company company = new Company();
        try {
            this.connect();
            String query = "SELECT id_company FROM `companies` WHERE `companies`.`email` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                company = this.findCompanyById(rs.getInt("id_company"));
            }
        } catch (Exception e) {
            System.out.println("CompanyDao: Error" + e.getMessage());
        }
        return company;
    }
}
