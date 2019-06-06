/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.model;

import java.util.Date;
/**
 *
 * @author Adrian Hoyos, Alexis Holguin github:MoraHol
 */
public class Offer {
    private int id;
    private Company company;
    private Category category;
    private Date createdAt;
    private String working_day;
    private String title;
    private String description;
    private String requirements;
    private int numberVacants;
    private Long salary;

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

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public int getNumberVacants() {
        return numberVacants;
    }

    public void setNumberVacants(int numberVacants) {
        this.numberVacants = numberVacants;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getWorking_day() {
        return working_day;
    }

    public void setWorking_day(String working_day) {
        this.working_day = working_day;
    }
    
}
