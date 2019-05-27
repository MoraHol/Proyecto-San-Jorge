/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.bean;

import com.sanjorge.model.Category;
import com.sanjorge.model.Company;
import com.sanjorge.model.User;
import com.sanjorge.service.CategoryService;
import com.sanjorge.service.CompanyService;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
@ManagedBean(name = "registerCompanyBean")
@ViewScoped
public class RegisterCompanyBean {

    private CategoryService categoryService;
    private CompanyService companyService;
    private int selectedCategory;
    private ArrayList<Category> categories;

    // fields 
    private String name;
    private byte[] contents;
    private String email;
    private String password;
    private String description;
    private String webpage;
    private String phoneNumber;
    private String nit;
    private String address;

    /**
     * Creates a new instance of RegisterCompany
     */
    public RegisterCompanyBean() {
        categoryService = new CategoryService();
        categories = categoryService.list();
        companyService = new CompanyService();
    }

    public void upload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        contents = uploadedFile.getContents();
        if (contents.length > 0) {
            FacesContext.getCurrentInstance().addMessage("messagesRegister", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se ha subido la imagen satisfactoriamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage("messagesRegister", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se ha subido la imagen satisfactoriamente"));
        }
    }

    public void insert() {
        try {
            Company company = new Company();
            company.setName(name);
            company.setEmail(email);
            company.setPhoneNumber(phoneNumber);
            company.setDescription(description);
            company.setPassword(companyService.convertSHA256(password));
            company.setAddress(address);
            company.setLogo(contents);
            company.setWebpage(webpage);
            company.setNit(nit);
            company.setCategory(categoryService.getCategoryById(selectedCategory));
            // guardar foto en base de datos
            try {
                if (companyService.insert(company) > 0) {
                    FacesContext.getCurrentInstance().addMessage("messagesRegister",
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha creado la Cuenta"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("messagesRegister",
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", e.getMessage()));
            }

        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println("error: " + e.getMessage());
        }
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
