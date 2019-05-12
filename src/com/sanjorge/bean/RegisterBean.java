/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.bean;

import com.sanjorge.model.User;
import com.sanjorge.service.UserService;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Alexis Holguin
 */
@ManagedBean(name = "registerBean")
@SessionScoped
public class RegisterBean implements Serializable {
    private UserService userService;
    private String firstName;
    private String secondName;
    private String firstSurName;
    private String secondSurName;
    private String password;
    private String email;
    private String gender;
    private Date birthdate;
    private String civilStatus;
    private String address;
    private String phoneNumber;
    private UploadedFile photo;
    private String profile;
    private String identificationNumber;

    @PostConstruct
    public void init() {
        userService = new UserService();
    }

    public String insert() {
        try {
            User user = new User();
            user.setFirstName(firstName);
            user.setSecondName(secondName);
            user.setFirstSurName(firstSurName);
            user.setSecondSurName(secondSurName);
            user.setEmail(email);
            user.setGender(gender);
            user.setBirthdate(birthdate);
            user.setCivilStatus(civilStatus);
            user.setPhoneNumber(phoneNumber);
            user.setProfile(profile);
            user.setPassword(userService.convertSHA256(password));
            user.setAddress(address);
            user.setIdentificationNumber(identificationNumber);
            // guardar foto en base de datos
            if (userService.insert(user) > 0) {
                FacesContext.getCurrentInstance().addMessage("messagesRegister",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha creado la Cuenta"));
            } else {
                FacesContext.getCurrentInstance().addMessage("messagesRegister",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se produjo un error al registrarse"));
            }
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println("error: " + e.getMessage());
        }
        return "";
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber.trim();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName.trim();
    }

  

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address.trim();
    }

    
}
