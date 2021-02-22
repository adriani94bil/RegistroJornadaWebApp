/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.servicios;

import com.registro.entidades.Usuario;
import com.registro.excepciones.LoginException;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@Stateless
public class LoginService {

    @PersistenceContext(unitName="ResgistroPU")
    private EntityManager em;
    
    public LoginService() {
        
    }
    
    @PostConstruct
    public void iniciar(){
        
    }
    public void login(String email, String clave,HttpSession session) throws LoginException{
        //Si existe email y clave
        Query query=em.createNamedQuery("Usuario.findAll");
        Collection<Usuario> usuarios=query.getResultList();
        Usuario userEncontrado=null;
        //Buscamos Usuario
        for (Usuario u:usuarios) {
            if (u.getEmail().equals(email) && u.getPassword().equals(clave)) {
                userEncontrado=u;
                break;
            }
        }
        //si existe añadir a session
        if (userEncontrado==null) {
            throw new LoginException("El usuario no existe. Debe registrarse.");
        } else {
            if (userEncontrado.getPassword().equals(clave)) {
                session.setAttribute("usuario", userEncontrado);
                
            }else{
                throw new LoginException("Clave no valida");
            }
        }
        // sino existe lanzo excepcion
        
    }
    public void logout(HttpSession session){
        session.invalidate();
    }
}
