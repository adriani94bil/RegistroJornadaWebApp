/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.web;

import com.registro.entidades.Horario;
import com.registro.entidades.Usuario;
import com.registro.excepciones.HorarioCreateException;
import com.registro.servicios.HorarioServiceLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author user
 */
@Named(value = "gestionHorariosMB")
@RequestScoped
public class GestionHorariosMB {
    @EJB
    private HorarioServiceLocal horarioService;
    
    @Inject
    private LoginManagedBean loginMB;
    
    private Usuario usuarioLog;
    
    private Horario horarioRegistro;
    private Horario horarioModificar;
    
    public GestionHorariosMB() {
        
    }
    
    @PostConstruct
    public void inicializar(){
        this.usuarioLog=loginMB.getUsuarioLog();
        this.horarioRegistro=new Horario();
        this.horarioModificar=new Horario();
    }

    public Horario getHorarioRegistro() {
        return horarioRegistro;
    }

    public void setHorarioRegistro(Horario horarioRegistro) {
        this.horarioRegistro = horarioRegistro;
    }

    public Horario getHorarioModificar() {
        return horarioModificar;
    }

    public void setHorarioModificar(Horario horarioModificar) {
        this.horarioModificar = horarioModificar;
    }
    
    
    //acciones
    
    public void altaHorario(){
        try {
            this.horarioRegistro.setUsuario(usuarioLog);
            horarioService.iniciarHora(horarioRegistro.getIdHorario());
        } catch (HorarioCreateException ex) {
            Logger.getLogger(GestionHorariosMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateHorario(){
        
    }
                
    
}
