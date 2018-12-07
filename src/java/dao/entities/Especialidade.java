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
@Table(name = "Especialidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Especialidade.findAll", query = "SELECT e FROM Especialidade e"),
    @NamedQuery(name = "Especialidade.findByCodigo", query = "SELECT e FROM Especialidade e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "Especialidade.findByIndice", query = "SELECT e FROM Especialidade e WHERE e.indice = :indice"),
    @NamedQuery(name = "Especialidade.findByNome", query = "SELECT e FROM Especialidade e WHERE e.nome = :nome")})
public class Especialidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Codigo")
    private Integer codigo;
    @Column(name = "Indice")
    private Integer indice;
    @Size(max = 200)
    @Column(name = "Nome")
    private String nome;
    @ManyToMany(mappedBy = "especialidadeCollection")
    private Collection<ConsultaDiagnostico> consultaDiagnosticoCollection;
    @ManyToMany(mappedBy = "especialidadeCollection")
    private Collection<Medico> medicoCollection;
    @JoinColumn(name = "fk_Taxa_Taxa", referencedColumnName = "Taxa")
    @ManyToOne
    private Taxa fkTaxaTaxa;

    public Especialidade() {
    }

    public Especialidade(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<ConsultaDiagnostico> getConsultaDiagnosticoCollection() {
        return consultaDiagnosticoCollection;
    }

    public void setConsultaDiagnosticoCollection(Collection<ConsultaDiagnostico> consultaDiagnosticoCollection) {
        this.consultaDiagnosticoCollection = consultaDiagnosticoCollection;
    }

    @XmlTransient
    public Collection<Medico> getMedicoCollection() {
        return medicoCollection;
    }

    public void setMedicoCollection(Collection<Medico> medicoCollection) {
        this.medicoCollection = medicoCollection;
    }

    public Taxa getFkTaxaTaxa() {
        return fkTaxaTaxa;
    }

    public void setFkTaxaTaxa(Taxa fkTaxaTaxa) {
        this.fkTaxaTaxa = fkTaxaTaxa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especialidade)) {
            return false;
        }
        Especialidade other = (Especialidade) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.entities.Especialidade[ codigo=" + codigo + " ]";
    }
    
}
