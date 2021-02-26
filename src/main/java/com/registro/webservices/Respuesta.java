/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.webservices;

import com.registro.entidades.Horario;
import com.registro.entidades.Usuario;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Respuesta implements Serializable{
    private boolean sinErrores;
    private String mensaje;
    private Horario horario;
    private Usuario usuario;

    public Respuesta() {
    }

    public boolean isSinErrores() {
        return sinErrores;
    }

    public void setSinErrores(boolean sinErrores) {
        this.sinErrores = sinErrores;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
