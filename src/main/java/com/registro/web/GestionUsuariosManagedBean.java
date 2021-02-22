/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.web;

import com.registro.entidades.Usuario;
import com.registro.excepciones.UsuarioNotFoundException;
import com.registro.excepciones.UsuarioUpdateException;
import com.registro.servicios.UsuarioServiceLocal;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author user
 */
@Named(value = "gestionUsuariosMB")
@RequestScoped
public class GestionUsuariosManagedBean {

    //lista con todos los usuarios
    
    private Collection<Usuario> coleccionUsuarios;
    private Usuario usuarioEncontrado;
    private Usuario usuarioAModificar;
    
    private String emailABuscar;
    
    @EJB
    private UsuarioServiceLocal usuarioService;
    
    public GestionUsuariosManagedBean() {
        
    }
    
    @PostConstruct
    public void inicializar(){
        this.coleccionUsuarios=usuarioService.getAllUsuarios();
        this.usuarioAModificar=new Usuario();
        this.usuarioEncontrado=new Usuario();
    }
    
    
    public Collection<Usuario> getColeccionUsuarios() {
        return coleccionUsuarios;
    }

    public Usuario getUsuarioEncontrado() {
        return usuarioEncontrado;
    }

    public Usuario getUsuarioAModificar() {
        return usuarioAModificar;
    }

    public void setUsuarioAModificar(Usuario usuarioAModificar) {
        this.usuarioAModificar = usuarioAModificar;
    }
    
    public String getEmailABuscar() {
        return emailABuscar;
    }

    public void setEmailABuscar(String emailABuscar) {
        this.emailABuscar = emailABuscar;
    }
    
    // acciones
    public String buscarUsuario(String email){
        return buscarPorMail(email);
    }
    public String buscarUsuarioPorEmailEntrada(){
        return buscarPorMail(this.emailABuscar);
    }
    public String borrar(int id){
        try {
            this.usuarioService.borrar(id);
            this.coleccionUsuarios=usuarioService.getAllUsuarios();
        } catch (UsuarioNotFoundException ex) {
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("No se pudo borrar"+ex.getMessage()) );
        }
        return "listaEmpleados";
    }
    public  String update(){
        try {
            this.usuarioService.modificar(usuarioAModificar);
            this.coleccionUsuarios=usuarioService.getAllUsuarios();
        } catch (UsuarioNotFoundException | UsuarioUpdateException ex) {
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("No se pudo modificar"+ex.getMessage()) );
        } 
        return "listaEmpleados";
    }
    
    //Método auxiliar 
    private String buscarPorMail(String email){
        try {
            this.usuarioEncontrado=usuarioService.getUsuarioByEmail(email);
            return "detalleUsuario";
        } catch (UsuarioNotFoundException ex) {
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(ex.getMessage()) );
            return null;
        }
    }
    
}
