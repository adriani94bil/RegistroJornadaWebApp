/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.web;

import com.registro.entidades.Horario;
import com.registro.entidades.Usuario;
import com.registro.excepciones.HorarioCreateException;
import com.registro.excepciones.HorarioNotFoundException;
import com.registro.excepciones.HorarioUpdateException;
import com.registro.servicios.HorarioServiceLocal;
import java.util.Collection;
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
    private Collection<Horario> allHorario;
    
    public GestionHorariosMB() {
        System.out.println("......instanciando GestionHorarioMB");
    }
    
    @PostConstruct
    public void inicializar(){
        this.usuarioLog=loginMB.getUsuarioLog();
        this.horarioRegistro=new Horario();
        this.horarioModificar=new Horario();
        this.allHorario=horarioService.getAllHorasPorEmpleado(this.usuarioLog.getIdUsuario());
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

    public Collection<Horario> getAllHorario() {
        return allHorario;
    }

    public void setAllHorario(Collection<Horario> allHorario) {
        this.allHorario = allHorario;
    }
    
    
    //acciones
    
    public void altaHorario(){
        try {
            horarioService.iniciarHora(usuarioLog.getIdUsuario());
            inicializar();
        } catch (HorarioCreateException ex) {
            Logger.getLogger(GestionHorariosMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateHorario(){
        try {
            horarioService.modificarHorario(usuarioLog.getIdUsuario());
            inicializar();
        } catch (HorarioNotFoundException | HorarioUpdateException ex) {
            Logger.getLogger(GestionHorariosMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
                
    
}
