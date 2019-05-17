
package com.sanjorge.idao;

import com.sanjorge.model.Aplication;
import com.sanjorge.model.User;
import java.util.ArrayList;

/**
 * @author David Viuche
 */
public interface IAplicationDao {
    public ArrayList<Aplication> findAll();
    public Aplication findAplicationByUser(User user);
    public int save(Aplication aplication);
    public int delete(User user);
    public int update(Aplication aplication);

}
