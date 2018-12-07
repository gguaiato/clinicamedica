/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "Doen\u00e7a")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doen\u00e7a.findAll", query = "SELECT d FROM Doen\u00e7a d"),
    @NamedQuery(name = "Doen\u00e7a.findByIdDoenca", query = "SELECT d FROM Doen\u00e7a d WHERE d.idDoenca = :idDoenca"),
    @NamedQuery(name = "Doen\u00e7a.findByNome", query = "SELECT d FROM Doen\u00e7a d WHERE d.nome = :nome")})
public class Doença implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idDoenca")
    private Integer idDoenca;
    @Size(max = 200)
    @Column(name = "Nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doen\u00e7a")
    private Collection<ConsultaDiagnostico> consultaDiagnosticoCollection;

    public Doença() {
    }

    public Doença(Integer idDoenca) {
        this.idDoenca = idDoenca;
    }

    public Integer getIdDoenca() {
        return idDoenca;
    }

    public void setIdDoenca(Integer idDoenca) {
        this.idDoenca = idDoenca;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDoenca != null ? idDoenca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doença)) {
            return false;
        }
        Doença other = (Doença) object;
        if ((this.idDoenca == null && other.idDoenca != null) || (this.idDoenca != null && !this.idDoenca.equals(other.idDoenca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.entities.Doen\u00e7a[ idDoenca=" + idDoenca + " ]";
    }
    
}
