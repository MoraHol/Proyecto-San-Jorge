
package com.sanjorge.idao;

import com.sanjorge.model.Company;
import java.util.ArrayList;

/**
 * @author David Viuche
 */
public interface ICompanyDao {
    public ArrayList<Company> findAll();
    public Company findCompanyById(int id);
    public int save(Company company) throws Exception;
    public int delete(int id);
    public int update(Company company);
    public Company findByEmail(String email);

}
