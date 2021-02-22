/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.web;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author user
 */
@Named(value = "idiomaMB")
@SessionScoped
public class IdiomaManagedBean implements Serializable {

    private String idiomaUser="es";


    public String getIdiomaUser() {
        return idiomaUser;
    }
    public IdiomaManagedBean() {
    }
    
    //Metodos
    
    public  String cambiarIdioma(String nuevo){
        this.idiomaUser=nuevo;
        return null;
    }
    
}
