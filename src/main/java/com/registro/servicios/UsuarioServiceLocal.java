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
import javax.ejb.Local;

@Local
public interface UsuarioServiceLocal {
    public Usuario getUsuario(int id) throws UsuarioNotFoundException;
    public void alta(Usuario usrNuevo) throws UsuarioCreateException;
    public Collection<Usuario> getAllUsuarios();
    public Usuario getUsuarioByEmail(String email) throws UsuarioNotFoundException;
    public void borrar(int id) throws UsuarioNotFoundException;
    public void modificar(Usuario user) throws UsuarioNotFoundException, UsuarioUpdateException;
}
