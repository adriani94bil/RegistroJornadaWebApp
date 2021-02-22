/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.web;

import com.registro.servicios.UsuarioServiceLocal;
import com.registro.entidades.Usuario;
import com.registro.excepciones.*;
import com.registro.servicios.LoginService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@Named(value = "loginMB")
@SessionScoped
public class LoginManagedBean implements Serializable {

    private String email;
    private String password;
    private Usuario usuarioLog;
    
    @EJB
    private LoginService loginService;
    
    @EJB
    private UsuarioServiceLocal usuarioService;
    
    
    public LoginManagedBean() {
        
    }
    @PostConstruct
    public void iniciarLogin(){
        this.usuarioLog=new Usuario();
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

    public Usuario getUsuarioLog() {
        return usuarioLog;
    }

    public void setUsuarioLog(Usuario usuarioLog) {
        this.usuarioLog = usuarioLog;
    }
    public String login(){
        FacesContext ctx=FacesContext.getCurrentInstance();
        try {            
            loginService.login(email, password, (HttpSession) ctx.getExternalContext().getSession(true));
            usuarioLog=usuarioService.getUsuarioByEmail(email);
            return "listaEmpleados";
        } catch (LoginException | UsuarioNotFoundException ex) {
            Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            this.email="";
            this.password="";
            FacesMessage msg=new FacesMessage(ex.getMessage());
            ctx.addMessage(null, msg);
            return "login";
        }
    }
    public String logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
            this.usuarioLog=null;
            this.email="";
            this.password="";
        }
        return "login?faces-redirect=true";
        
    }
    
}
