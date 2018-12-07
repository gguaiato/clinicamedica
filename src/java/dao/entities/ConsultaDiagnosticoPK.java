/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gabriel
 */
@Embeddable
public class ConsultaDiagnosticoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idConsulta")
    private int idConsulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idDiagnostico")
    private int idDiagnostico;

    public ConsultaDiagnosticoPK() {
    }

    public ConsultaDiagnosticoPK(int idConsulta, int idDiagnostico) {
        this.idConsulta = idConsulta;
        this.idDiagnostico = idDiagnostico;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idConsulta;
        hash += (int) idDiagnostico;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultaDiagnosticoPK)) {
            return false;
        }
        ConsultaDiagnosticoPK other = (ConsultaDiagnosticoPK) object;
        if (this.idConsulta != other.idConsulta) {
            return false;
        }
        if (this.idDiagnostico != other.idDiagnostico) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.entities.ConsultaDiagnosticoPK[ idConsulta=" + idConsulta + ", idDiagnostico=" + idDiagnostico + " ]";
    }
    
}
