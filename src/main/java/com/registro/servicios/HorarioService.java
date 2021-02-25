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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

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
    public void iniciarHora(Integer idUsuario) throws HorarioCreateException {
        
        /*
        1. Validar que el idEmpleado no sea null
        2. ver si no está ya la jornada iniciada para el empleado
           Jornada iniciada es que hay un registro de jornada para el empleado 
            con fecha incio la de hoy y fecha fin null
        3. si hay jornada iniciada enciar excepcion
        4.sino insert registro jornada fecha hoy y null fecha fin
        */
        if (idUsuario==null) {
            throw new HorarioCreateException("Debe indicar el id de empleado");
        }
        Query query=em.createNamedQuery("Horario.findByJornadasNoFinalizadasPorEmpleado");
        query.setParameter("idUsuario", idUsuario);
        Date hoy=new Date();
        query.setParameter("fecha", hoy,TemporalType.DATE);
        List<Horario> resultado= query.getResultList();
        if (resultado.size()>0) {
            throw new HorarioCreateException("La jornada ya está iniciada");
        }
        
        //grabar
        
        Horario h=new Horario();
        h.setIdUsuario(idUsuario);
        h.setFecha(hoy);
        h.setHoraEntrada(hoy); // aqui solo guarda horas min y sec por la anotacion que tiene en el atributo
        h.setHoraSalida(null);
        em.persist(h);
        
        
    }

    @Override
    public void modificarHorario(Integer idUsuario) throws HorarioNotFoundException, HorarioUpdateException {
        Query query=em.createNamedQuery("Horario.findByJornadaNoFinalizada");
        query.setParameter("idUsuario", idUsuario);
        Date hoyFin=new Date();
        //List<Horario> resultado= query.getResultList();
        Horario horarioMB= (Horario)query.getSingleResult();
//        if (resultado.size()==0) {
//            throw new HorarioNotFoundException("No hay jornadas iniciadas");
//        }
        if (horarioMB==null) {
            throw new HorarioNotFoundException("No hay jornadas iniciadas");
        }

//        Horario horarioMB=resultado.get(resultado.size()-1);
        horarioMB.setHoraSalida(hoyFin);
    }

    @Override
    public Collection<Horario> getAllHorasPorEmpleado(Integer idUsuario) {
        Query query=em.createNamedQuery("Horario.findByIdEmpleado");
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }
    
    
}
