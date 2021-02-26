/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.webservices;

import com.registro.entidades.Horario;
import com.registro.entidades.Usuario;
import com.registro.excepciones.HorarioCreateException;
import com.registro.excepciones.HorarioNotFoundException;
import com.registro.excepciones.HorarioUpdateException;
import com.registro.excepciones.UsuarioNotFoundException;
import com.registro.servicios.HorarioServiceLocal;
import com.registro.servicios.LoginService;
import com.registro.servicios.UsuarioServiceLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Path("jornadas")
public class RegistroJornadasRestFullWS {
    
    @EJB
    private LoginService servicioLogin;
    @EJB
    private HorarioServiceLocal servicio;
    @EJB
    private UsuarioServiceLocal usuarioService;
    

    @GET
    @Path("usuario/{idUsuario}")
    @Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Horario> find(@PathParam("idUsuario") Integer id) {
        List<Horario> lista=(List<Horario>) servicio.getAllHorasPorEmpleado(id);
        return lista;
    }
    //Mando todos los datos a la aplicacion movil
    @GET
    @Path("usuarioMobile/{idUsuario}")
    @Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Usuario findUsuarioMobile(@PathParam("idUsuario") Integer id) throws UsuarioNotFoundException {
        Usuario usuario= (Usuario) usuarioService.getUsuario(id);
        return usuario;
    } 
    
    @GET()
    @Path("iniciarJornada/usuario/{idUsuario}")
    @Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Respuesta iniciarJornada(@PathParam("idUsuario") Integer id) throws UsuarioNotFoundException{
        Respuesta respuesta=new Respuesta();
        Usuario usuario= (Usuario) usuarioService.getUsuario(id);
        Horario horario= (Horario) servicio.getLastHorario(id);
        try{
            servicio.iniciarHora(id);
            respuesta.setSinErrores(true);
            respuesta.setMensaje("Jornada Iniciada");
            respuesta.setHorario(horario);
            respuesta.setUsuario(usuario);
        }catch(HorarioCreateException ex){
            respuesta.setSinErrores(false);
            respuesta.setMensaje(ex.getMessage());
        }
        return respuesta;
    }
    
    @GET()
    @Path("finalizarJornada/usuario/{idUsuario}")
    @Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Respuesta finalizarJornada(@PathParam("idUsuario") Integer id) throws UsuarioNotFoundException {
        Respuesta respuesta=new Respuesta();
        Usuario usuario= (Usuario) usuarioService.getUsuario(id);
        Horario horario= (Horario) servicio.getLastHorario(id);
        try {
            servicio.modificarHorario(id);
            respuesta.setSinErrores(true);
            respuesta.setHorario(horario);
            respuesta.setUsuario(usuario);
            respuesta.setMensaje("Jornada Finalizada");
        } catch (HorarioNotFoundException | HorarioUpdateException ex) {
            respuesta.setSinErrores(false);
            respuesta.setMensaje(ex.getMessage());
        }
        return respuesta;
    }
}
