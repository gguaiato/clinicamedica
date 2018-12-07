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
@Table(name = "Taxa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taxa.findAll", query = "SELECT t FROM Taxa t"),
    @NamedQuery(name = "Taxa.findByTaxa", query = "SELECT t FROM Taxa t WHERE t.taxa = :taxa"),
    @NamedQuery(name = "Taxa.findByAno", query = "SELECT t FROM Taxa t WHERE t.ano = :ano"),
    @NamedQuery(name = "Taxa.findByMes", query = "SELECT t FROM Taxa t WHERE t.mes = :mes"),
    @NamedQuery(name = "Taxa.findByValor", query = "SELECT t FROM Taxa t WHERE t.valor = :valor")})
public class Taxa implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Taxa")
    private Double taxa;
    @Column(name = "Ano")
    @Temporal(TemporalType.DATE)
    private Date ano;
    @Column(name = "Mes")
    @Temporal(TemporalType.DATE)
    private Date mes;
    @Column(name = "Valor")
    private Double valor;
    @OneToMany(mappedBy = "fkTaxaTaxa")
    private Collection<Especialidade> especialidadeCollection;

    public Taxa() {
    }

    public Taxa(Double taxa) {
        this.taxa = taxa;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    public Date getAno() {
        return ano;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }

    public Date getMes() {
        return mes;
    }

    public void setMes(Date mes) {
        this.mes = mes;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @XmlTransient
    public Collection<Especialidade> getEspecialidadeCollection() {
        return especialidadeCollection;
    }

    public void setEspecialidadeCollection(Collection<Especialidade> especialidadeCollection) {
        this.especialidadeCollection = especialidadeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxa != null ? taxa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taxa)) {
            return false;
        }
        Taxa other = (Taxa) object;
        if ((this.taxa == null && other.taxa != null) || (this.taxa != null && !this.taxa.equals(other.taxa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.entities.Taxa[ taxa=" + taxa + " ]";
    }
    
}
