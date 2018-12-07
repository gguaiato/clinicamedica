/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name = "Agenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agenda.findAll", query = "SELECT a FROM Agenda a"),
    @NamedQuery(name = "Agenda.findByIdAgenda", query = "SELECT a FROM Agenda a WHERE a.idAgenda = :idAgenda"),
    @NamedQuery(name = "Agenda.findByDiaSemana", query = "SELECT a FROM Agenda a WHERE a.diaSemana = :diaSemana"),
    @NamedQuery(name = "Agenda.findByHoraInicio", query = "SELECT a FROM Agenda a WHERE a.horaInicio = :horaInicio"),
    @NamedQuery(name = "Agenda.findByHoraFim", query = "SELECT a FROM Agenda a WHERE a.horaFim = :horaFim")})
public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAgenda")
    private Integer idAgenda;
    @Column(name = "DiaSemana")
    @Temporal(TemporalType.DATE)
    private Date diaSemana;
    @Column(name = "HoraInicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "HoraFim")
    @Temporal(TemporalType.TIME)
    private Date horaFim;
    @OneToMany(mappedBy = "fkAgendaidAgenda")
    private Collection<Medico> medicoCollection;

    public Agenda() {
    }

    public Agenda(Integer idAgenda) {
        this.idAgenda = idAgenda;
    }

    public Integer getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Integer idAgenda) {
        this.idAgenda = idAgenda;
    }

    public Date getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Date diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    @XmlTransient
    public Collection<Medico> getMedicoCollection() {
        return medicoCollection;
    }

    public void setMedicoCollection(Collection<Medico> medicoCollection) {
        this.medicoCollection = medicoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgenda != null ? idAgenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agenda)) {
            return false;
        }
        Agenda other = (Agenda) object;
        if ((this.idAgenda == null && other.idAgenda != null) || (this.idAgenda != null && !this.idAgenda.equals(other.idAgenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.entities.Agenda[ idAgenda=" + idAgenda + " ]";
    }
    
}
