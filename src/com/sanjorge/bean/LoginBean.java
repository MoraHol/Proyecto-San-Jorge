package com.sanjorge.bean;

import com.sanjorge.model.User;
import com.sanjorge.service.UserService;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String email;
    private String password;
    private UserService userService;
    private User user;

    @PostConstruct
    public void init() {
        email = new String();
        password = new String();
        userService = new UserService();
    }

    public boolean isAuthenticated() throws Exception {
        System.out.println(email + " " + password);
        user = userService.authenticateUser(email, password);
        return user != null;
    }

    public void verifySession() {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Proyecto-San-Jorge/login.xhtml");
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Por favor inicia Sesión"));
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void doLogin() throws Exception {
        try {
            if (isAuthenticated()) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
                FacesContext.getCurrentInstance().getExternalContext().redirect("pages/user/dashboard.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesApp",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void sendEmail() {
        String destinatario = "wholguinmor@uniminuto.edu.co";
        String cuerpo = "<h1>hola negro</h1>";
        String asunto = "pruba";
        String clave = "30082000";
        String remitente = "holguinalexis30";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(remitente));  
            message.addRecipients(Message.RecipientType.TO, destinatario); //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setContent(cuerpo,"text/html");
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }

    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
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

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
