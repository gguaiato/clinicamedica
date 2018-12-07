/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name = "Consulta_Diagnostico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultaDiagnostico.findAll", query = "SELECT c FROM ConsultaDiagnostico c"),
    @NamedQuery(name = "ConsultaDiagnostico.findByIdConsulta", query = "SELECT c FROM ConsultaDiagnostico c WHERE c.consultaDiagnosticoPK.idConsulta = :idConsulta"),
    @NamedQuery(name = "ConsultaDiagnostico.findByData", query = "SELECT c FROM ConsultaDiagnostico c WHERE c.data = :data"),
    @NamedQuery(name = "ConsultaDiagnostico.findByPagou", query = "SELECT c FROM ConsultaDiagnostico c WHERE c.pagou = :pagou"),
    @NamedQuery(name = "ConsultaDiagnostico.findByValorPago", query = "SELECT c FROM ConsultaDiagnostico c WHERE c.valorPago = :valorPago"),
    @NamedQuery(name = "ConsultaDiagnostico.findByFormaPagamento", query = "SELECT c FROM ConsultaDiagnostico c WHERE c.formaPagamento = :formaPagamento"),
    @NamedQuery(name = "ConsultaDiagnostico.findByHoraInicio", query = "SELECT c FROM ConsultaDiagnostico c WHERE c.horaInicio = :horaInicio"),
    @NamedQuery(name = "ConsultaDiagnostico.findByMinutosInic", query = "SELECT c FROM ConsultaDiagnostico c WHERE c.minutosInic = :minutosInic"),
    @NamedQuery(name = "ConsultaDiagnostico.findByHoraFim", query = "SELECT c FROM ConsultaDiagnostico c WHERE c.horaFim = :horaFim"),
    @NamedQuery(name = "ConsultaDiagnostico.findByTratamentoRecomendado", query = "SELECT c FROM ConsultaDiagnostico c WHERE c.tratamentoRecomendado = :tratamentoRecomendado"),
    @NamedQuery(name = "ConsultaDiagnostico.findByIdDiagnostico", query = "SELECT c FROM ConsultaDiagnostico c WHERE c.consultaDiagnosticoPK.idDiagnostico = :idDiagnostico"),
    @NamedQuery(name = "ConsultaDiagnostico.findByRemediosReceitados", query = "SELECT c FROM ConsultaDiagnostico c WHERE c.remediosReceitados = :remediosReceitados"),
    @NamedQuery(name = "ConsultaDiagnostico.findByObservacoes", query = "SELECT c FROM ConsultaDiagnostico c WHERE c.observacoes = :observacoes")})
public class ConsultaDiagnostico implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsultaDiagnosticoPK consultaDiagnosticoPK;
    @Column(name = "Data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "Pagou")
    private Boolean pagou;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ValorPago")
    private Double valorPago;
    @Size(max = 10)
    @Column(name = "FormaPagamento")
    private String formaPagamento;
    @Column(name = "HoraInicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "MinutosInic")
    @Temporal(TemporalType.TIME)
    private Date minutosInic;
    @Column(name = "HoraFim")
    @Temporal(TemporalType.TIME)
    private Date horaFim;
    @Size(max = 60)
    @Column(name = "TratamentoRecomendado")
    private String tratamentoRecomendado;
    @Size(max = 500)
    @Column(name = "RemediosReceitados")
    private String remediosReceitados;
    @Size(max = 200)
    @Column(name = "Observacoes")
    private String observacoes;
    @JoinTable(name = "Especifica", joinColumns = {
        @JoinColumn(name = "fk_Consulta_Diagnostico_idConsulta", referencedColumnName = "idConsulta"),
        @JoinColumn(name = "fk_Consulta_Diagnostico_idDiagnostico", referencedColumnName = "idDiagnostico")}, inverseJoinColumns = {
        @JoinColumn(name = "fk_Especialidade_Codigo", referencedColumnName = "Codigo")})
    @ManyToMany
    private Collection<Especialidade> especialidadeCollection;
    @JoinColumn(name = "idDiagnostico", referencedColumnName = "idDoenca", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Doença doença;
    @OneToMany(mappedBy = "consultaDiagnostico")
    private Collection<Paciente> pacienteCollection;
    @OneToMany(mappedBy = "consultaDiagnostico")
    private Collection<Medico> medicoCollection;

    public ConsultaDiagnostico() {
    }

    public ConsultaDiagnostico(ConsultaDiagnosticoPK consultaDiagnosticoPK) {
        this.consultaDiagnosticoPK = consultaDiagnosticoPK;
    }

    public ConsultaDiagnostico(int idConsulta, int idDiagnostico) {
        this.consultaDiagnosticoPK = new ConsultaDiagnosticoPK(idConsulta, idDiagnostico);
    }

    public ConsultaDiagnosticoPK getConsultaDiagnosticoPK() {
        return consultaDiagnosticoPK;
    }

    public void setConsultaDiagnosticoPK(ConsultaDiagnosticoPK consultaDiagnosticoPK) {
        this.consultaDiagnosticoPK = consultaDiagnosticoPK;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Boolean getPagou() {
        return pagou;
    }

    public void setPagou(Boolean pagou) {
        this.pagou = pagou;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getMinutosInic() {
        return minutosInic;
    }

    public void setMinutosInic(Date minutosInic) {
        this.minutosInic = minutosInic;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    public String getTratamentoRecomendado() {
        return tratamentoRecomendado;
    }

    public void setTratamentoRecomendado(String tratamentoRecomendado) {
        this.tratamentoRecomendado = tratamentoRecomendado;
    }

    public String getRemediosReceitados() {
        return remediosReceitados;
    }

    public void setRemediosReceitados(String remediosReceitados) {
        this.remediosReceitados = remediosReceitados;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @XmlTransient
    public Collection<Especialidade> getEspecialidadeCollection() {
        return especialidadeCollection;
    }

    public void setEspecialidadeCollection(Collection<Especialidade> especialidadeCollection) {
        this.especialidadeCollection = especialidadeCollection;
    }

    public Doença getDoença() {
        return doença;
    }

    public void setDoença(Doença doença) {
        this.doença = doença;
    }

    @XmlTransient
    public Collection<Paciente> getPacienteCollection() {
        return pacienteCollection;
    }

    public void setPacienteCollection(Collection<Paciente> pacienteCollection) {
        this.pacienteCollection = pacienteCollection;
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
        hash += (consultaDiagnosticoPK != null ? consultaDiagnosticoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultaDiagnostico)) {
            return false;
        }
        ConsultaDiagnostico other = (ConsultaDiagnostico) object;
        if ((this.consultaDiagnosticoPK == null && other.consultaDiagnosticoPK != null) || (this.consultaDiagnosticoPK != null && !this.consultaDiagnosticoPK.equals(other.consultaDiagnosticoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.entities.ConsultaDiagnostico[ consultaDiagnosticoPK=" + consultaDiagnosticoPK + " ]";
    }
    
}
