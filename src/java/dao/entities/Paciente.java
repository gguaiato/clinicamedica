/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name = "Paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByCodigoPaciente", query = "SELECT p FROM Paciente p WHERE p.codigoPaciente = :codigoPaciente"),
    @NamedQuery(name = "Paciente.findByCpf", query = "SELECT p FROM Paciente p WHERE p.cpf = :cpf"),
    @NamedQuery(name = "Paciente.findByNome", query = "SELECT p FROM Paciente p WHERE p.nome = :nome"),
    @NamedQuery(name = "Paciente.findByTelefone", query = "SELECT p FROM Paciente p WHERE p.telefone = :telefone"),
    @NamedQuery(name = "Paciente.findBySexo", query = "SELECT p FROM Paciente p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Paciente.findByEndereco", query = "SELECT p FROM Paciente p WHERE p.endereco = :endereco"),
    @NamedQuery(name = "Paciente.findByIdade", query = "SELECT p FROM Paciente p WHERE p.idade = :idade")})
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CodigoPaciente")
    private Integer codigoPaciente;
    @Column(name = "CPF")
    private Long cpf;
    @Size(max = 200)
    @Column(name = "Nome")
    private String nome;
    @Column(name = "Telefone")
    private Long telefone;
    @Column(name = "Sexo")
    private Boolean sexo;
    @Size(max = 200)
    @Column(name = "Endereco")
    private String endereco;
    @Column(name = "Idade")
    private Integer idade;
    @JoinColumns({
        @JoinColumn(name = "fk_Consulta_Diagnostico_idConsulta", referencedColumnName = "idConsulta"),
        @JoinColumn(name = "fk_Consulta_Diagnostico_idDiagnostico", referencedColumnName = "idDiagnostico")})
    @ManyToOne
    private ConsultaDiagnostico consultaDiagnostico;

    public Paciente() {
    }

    public Paciente(Integer codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public Integer getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(Integer codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
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

    public Boolean getSexo() {
        return sexo;
    }

    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
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
        hash += (codigoPaciente != null ? codigoPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.codigoPaciente == null && other.codigoPaciente != null) || (this.codigoPaciente != null && !this.codigoPaciente.equals(other.codigoPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.entities.Paciente[ codigoPaciente=" + codigoPaciente + " ]";
    }
    
}
