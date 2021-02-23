/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.servicios;

import com.registro.entidades.Horario;
import com.registro.excepciones.*;
import javax.ejb.Local;

@Local
public interface HorarioServiceLocal {
    public Horario getHorario(int id) throws HorarioNotFoundException;
    public void altaHora(Horario horario) throws HorarioCreateException;
    public void modificarHorario(Horario horario) throws HorarioNotFoundException, HorarioUpdateException;
}
