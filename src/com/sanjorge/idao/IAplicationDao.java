
package com.sanjorge.idao;

import com.sanjorge.model.Aplication;
import com.sanjorge.model.Offer;
import java.util.ArrayList;

/**
 * @author David Viuche
 */
public interface IAplicationDao {
    public ArrayList<Aplication> findAll();
    public ArrayList<Aplication> findAplicationByUser(int idUser);
    public int save(Aplication aplication) throws Exception;
    public int delete(int idUser);
    public int update(Aplication aplication);
    public ArrayList<Aplication> findApplicationsByOffer(Offer offer);
}
