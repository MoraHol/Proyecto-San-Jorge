package com.sanjorge.model;

import java.util.Date;

/**
 * @author David Viuche
 */
public class Aplication {
    private int id;
    private Offer Offer;
    private User user;
    private Date created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Offer getOffer() {
        return Offer;
    }

    public void setOffer(Offer Offer) {
        this.Offer = Offer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

   
}
