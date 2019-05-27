/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.bean;

import com.sanjorge.dao.OfferDaoImpl;
import com.sanjorge.idao.IOfferDao;
import com.sanjorge.model.Category;
import com.sanjorge.model.Company;
import com.sanjorge.model.Offer;
import com.sanjorge.service.CategoryService;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexis
 */
@ManagedBean(name = "createOffersBean")
@RequestScoped
public class CreateOffersBean {

    private CategoryService categoryService;
    private int selectedCategory;
    private ArrayList<Category> categories;
    private IOfferDao offerDao;

    //fields
    private String title;
    private String description;
    private String requerimients;
    private double salary;
    private String working_day;
    private int numberVacants;

    /**
     * Creates a new instance of CreateOffersBean
     */
    public CreateOffersBean() {
        categoryService = new CategoryService();
        categories = categoryService.list();
        offerDao = new OfferDaoImpl();
    }

    public void create() {
        Offer offer = new Offer();
        offer.setCategory(categoryService.getCategoryById(selectedCategory));
        offer.setTitle(title);
        offer.setCompany((Company) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("company"));
        offer.setDescription(description);
        offer.setRequirements(requerimients);
        offer.setSalary((long) salary);
        offer.setWorking_day(working_day);
        offer.setNumberVacants(numberVacants);
        if(offerDao.createOffer(offer) > 0){
            FacesContext.getCurrentInstance().addMessage("messagesApp",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se ha creado la oferta"));
        }else{
            return;
        }
        
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public int getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(int selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequerimients() {
        return requerimients;
    }

    public void setRequerimients(String requerimients) {
        this.requerimients = requerimients;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getWorking_day() {
        return working_day;
    }

    public void setWorking_day(String working_day) {
        this.working_day = working_day;
    }

    public int getNumberVacants() {
        return numberVacants;
    }

    public void setNumberVacants(int numberVacants) {
        this.numberVacants = numberVacants;
    }

}
