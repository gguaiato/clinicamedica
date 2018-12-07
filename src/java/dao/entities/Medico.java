/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name = "Medico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medico.findAll", query = "SELECT m FROM Medico m"),
    @NamedQuery(name = "Medico.findByCrm", query = "SELECT m FROM Medico m WHERE m.crm = :crm"),
    @NamedQuery(name = "Medico.findByNome", query = "SELECT m FROM Medico m WHERE m.nome = :nome"),
    @NamedQuery(name = "Medico.findByTelefone", query = "SELECT m FROM Medico m WHERE m.telefone = :telefone")})
public class Medico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CRM")
    private Integer crm;
    @Size(max = 200)
    @Column(name = "Nome")
    private String nome;
    @Column(name = "Telefone")
    private Long telefone;
    @JoinTable(name = "Exerce", joinColumns = {
        @JoinColumn(name = "fk_Medico_CRM", referencedColumnName = "CRM")}, inverseJoinColumns = {
        @JoinColumn(name = "fk_Especialidade_Codigo", referencedColumnName = "Codigo")})
    @ManyToMany
    private Collection<Especialidade> especialidadeCollection;
    @JoinColumn(name = "fk_Agenda_idAgenda", referencedColumnName = "idAgenda")
    @ManyToOne
    private Agenda fkAgendaidAgenda;
    @JoinColumns({
        @JoinColumn(name = "fk_Consulta_Diagnostico_idConsulta", referencedColumnName = "idConsulta"),
        @JoinColumn(name = "fk_Consulta_Diagnostico_idDiagnostico", referencedColumnName = "idDiagnostico")})
    @ManyToOne
    private ConsultaDiagnostico consultaDiagnostico;

    public Medico() {
    }

    public Medico(Integer crm) {
        this.crm = crm;
    }

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    @XmlTransient
    public Collection<Especialidade> getEspecialidadeCollection() {
        return especialidadeCollection;
    }

    public void setEspecialidadeCollection(Collection<Especialidade> especialidadeCollection) {
        this.especialidadeCollection = especialidadeCollection;
    }

    public Agenda getFkAgendaidAgenda() {
        return fkAgendaidAgenda;
    }

    public void setFkAgendaidAgenda(Agenda fkAgendaidAgenda) {
        this.fkAgendaidAgenda = fkAgendaidAgenda;
    }

    public ConsultaDiagnostico getConsultaDiagnostico() {
        return consultaDiagnostico;
    }

    public void setConsultaDiagnostico(ConsultaDiagnostico consultaDiagnostico) {
        this.consultaDiagnostico = consultaDiagnostico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crm != null ? crm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medico)) {
            return false;
        }
        Medico other = (Medico) object;
        if ((this.crm == null && other.crm != null) || (this.crm != null && !this.crm.equals(other.crm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.entities.Medico[ crm=" + crm + " ]";
    }
    
}
