/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "HORARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByIdHorario", query = "SELECT h FROM Horario h WHERE h.idHorario = :idHorario"),
    @NamedQuery(name = "Horario.findByFecha", query = "SELECT h FROM Horario h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "Horario.findByHoraEntrada", query = "SELECT h FROM Horario h WHERE h.horaEntrada = :horaEntrada"),
    @NamedQuery(name = "Horario.findByIdEmpleado", query = "SELECT h FROM Horario h WHERE h.idUsuario = :idUsuario"),
    @NamedQuery(name = "Horario.findByJornadasNoFinalizadasPorEmpleado", 
            query = "SELECT h FROM Horario h WHERE h.idUsuario = :idUsuario AND h.fecha = :fecha AND h.horaSalida IS NULL"),
    @NamedQuery(name = "Horario.findByJornadaNoFinalizada", 
            query = "SELECT h FROM Horario h WHERE h.idUsuario = :idUsuario AND h.horaSalida IS NULL")
})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HORARIO")
    private Integer idHorario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA_ENTRADA")
    @Temporal(TemporalType.TIME)
    private Date horaEntrada;
    @Column(name = "HORA_SALIDA")
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
    
    @Column(name="ID_USUARIO")
    private Integer idUsuario;
    
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO",
            insertable = false, updatable=false
    )
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Horario() {
    }

    public Horario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Horario(Integer idHorario, Date fecha, Date horaEntrada) {
        this.idHorario = idHorario;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario idUsuario) {
        this.usuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorario != null ? idHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.idHorario == null && other.idHorario != null) || (this.idHorario != null && !this.idHorario.equals(other.idHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.registro.entidades.Horario[ idHorario=" + idHorario + " ]";
    }
    
}
