/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.webservices;

import com.registro.entidades.Horario;
import com.registro.excepciones.HorarioCreateException;
import com.registro.servicios.HorarioServiceLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author user
 */
@Stateless
@Path("jornadas")
public class RegistroJornadasRestFullWS {
    
    @EJB
    private HorarioServiceLocal servicio;
    

    @GET
    @Path("usuario/{idUsuario}")
    @Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Horario> find(@PathParam("idUsuario") Integer id) {
        List<Horario> lista=(List<Horario>) servicio.getAllHorasPorEmpleado(id);
        return lista;
    }
    
    @GET()
    @Path("iniciarJornada/usuario/{idUsuario}")
    //@Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public void iniciarJornada(@PathParam("idUsuario") Integer id) throws HorarioCreateException{
        servicio.iniciarHora(id);
    }
}
