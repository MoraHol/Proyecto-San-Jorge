
package com.sanjorge.idao;

import com.sanjorge.model.Aplication;
import java.util.ArrayList;

/**
 * @author David Viuche
 */
public interface IAplicationDao {
    public ArrayList<Aplication> findAll();
    public Aplication findAplicationByUser(int idUser);
    public int save(Aplication aplication);
    public int delete(int idUser);
    public int update(Aplication aplication);

}
