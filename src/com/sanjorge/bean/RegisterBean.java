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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Alexis Holguin github:MoraHol
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
    private String profile;
    private Integer identificationNumber;
    private boolean experience;
    private byte[] contents;

    @PostConstruct
    public void init() {
        userService = new UserService();
    }

    public void insert() {
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
            user.setPhoto(contents);
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
    }

    public void resetInput() {
        FacesContext.getCurrentInstance().addMessage("messagesRegister",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "El formulario ha sido limpiado"));
    }

    public void upload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        contents = uploadedFile.getContents();
        if (contents.length > 0) {
            FacesContext.getCurrentInstance().addMessage("messagesRegister", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se ha subido la imagen satisfactoriamente"));
        }else{
            FacesContext.getCurrentInstance().addMessage("messagesRegister", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se ha subido la imagen satisfactoriamente"));
        }
        // ... Save it, now!
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        this.password = password.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address.trim();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getFirstSurName() {
        return firstSurName;
    }

    public void setFirstSurName(String firstSurName) {
        this.firstSurName = firstSurName;
    }

    public String getSecondSurName() {
        return secondSurName;
    }

    public void setSecondSurName(String secondSurName) {
        this.secondSurName = secondSurName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Integer getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(Integer identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public boolean isExperience() {
        return experience;
    }

    public void setExperience(boolean experience) {
        this.experience = experience;
    }

}
