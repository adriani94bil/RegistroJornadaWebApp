/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.excepciones;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class HorarioCreateException extends Exception{

    public HorarioCreateException(String string) {
        super(string);
    }
    
}
