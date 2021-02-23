/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.servicios;

import com.registro.entidades.Horario;
import com.registro.excepciones.HorarioCreateException;
import com.registro.excepciones.HorarioNotFoundException;
import com.registro.excepciones.HorarioUpdateException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HorarioService implements HorarioServiceLocal{
    @PersistenceContext(unitName="RegistroPU")
    private EntityManager em;
    
    @Override
    public Horario getHorario(int id) throws HorarioNotFoundException {
        Horario h=em.find(Horario.class, id);
        if (h==null) {
            throw new HorarioNotFoundException("No existe el usuario solicitado");
        }
        return h;
    }

    @Override
    public void altaHora(Horario horario) throws HorarioCreateException {
        em.persist(horario);
    }

    @Override
    public void modificarHorario(Horario horario) throws HorarioNotFoundException, HorarioUpdateException {
        Horario horarioMB=this.getHorario(horario.getIdHorario());
        em.merge(horario);
    }
    
}
