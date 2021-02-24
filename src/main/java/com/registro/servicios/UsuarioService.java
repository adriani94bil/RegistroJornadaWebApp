/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.servicios;

import com.registro.entidades.Usuario;
import com.registro.excepciones.UsuarioCreateException;
import com.registro.excepciones.UsuarioNotFoundException;
import com.registro.excepciones.UsuarioUpdateException;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
public class UsuarioService implements UsuarioServiceLocal {
    
    @PersistenceContext(unitName="RegistroPU")
    private EntityManager em;
    
    @Override
    public Usuario getUsuario(int id) throws UsuarioNotFoundException {
        Usuario u=em.find(Usuario.class, id);
        if (u==null) {
            throw new UsuarioNotFoundException("No existe el usuario solicitado");
        }
        return u;
    }

    @Override
    public void alta(Usuario usrNuevo) throws UsuarioCreateException{
            em.persist(usrNuevo);
    }

    @Override
    public Collection<Usuario> getAllUsuarios() {
        //OJO jpa query SELECT u FROM Usuario u 
//        String consulta="SELECT u FROM Usuario u";
        Query query=em.createNamedQuery("Usuario.findAll");
        return query.getResultList();
    }

    @Override
    public Usuario getUsuarioByEmail(String email) throws UsuarioNotFoundException {
        Query query=em.createNamedQuery("Usuario.findByEmail");
        query.setParameter("email", email);
        try{
            Usuario usr=(Usuario) query.getSingleResult();
            return usr;
        } catch(javax.persistence.NoResultException e){
            throw new UsuarioNotFoundException("no existe un usuario con el email");
        }
    }

    @Override
    public void borrar(int id) throws UsuarioNotFoundException {
        Usuario userDB= this.getUsuario(id);
        userDB.setActivo(false);
        //em.merge(userDB);
    }

    @Override
    public void modificar(Usuario user) throws UsuarioNotFoundException, UsuarioUpdateException {
        Usuario usrBD= this.getUsuario(user.getIdUsuario());
//        usrBD.setEmail(usr.getEmail());
//        usrBD.setNombre(usr.getNombre());
//        usrBD.setPassword(usr.getPassword());
//      Los métodos de EJB hace commit al final si no hay excepciones
//      Commit --> hace que se sincronice los objetos de em con las tablas de la bd
        em.merge(user);
    }
}
