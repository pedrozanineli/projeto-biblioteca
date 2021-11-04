/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Samuel
 */
@Entity
@Table(name = "reserva")
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
    @NamedQuery(name = "Reserva.findByCodReserva", query = "SELECT r FROM Reserva r WHERE r.codReserva = :codReserva"),
    @NamedQuery(name = "Reserva.findByData", query = "SELECT r FROM Reserva r WHERE r.data = :data"),
    @NamedQuery(name = "Reserva.findByHorario", query = "SELECT r FROM Reserva r WHERE r.horario = :horario"),
    @NamedQuery(name = "Reserva.findByRaEmReserva", query = "SELECT r.alunora FROM Reserva r WHERE r.codReserva= :codReserva")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codReserva")
    private String codReserva;
    @Basic(optional = false)
    @Column(name = "Data")
    private String data;
    @Basic(optional = false)
    @Column(name = "Horario")
    private String horario;
    @JoinColumn(name = "Aluno_ra", referencedColumnName = "ra")
    @ManyToOne(optional = false)
    private Aluno alunora;
    @JoinColumn(name = "Sala_numSala", referencedColumnName = "numSala")
    @ManyToOne(optional = false)
    private Sala salanumSala;

    public Reserva() {
    }

    public Reserva(String codReserva, String data, String horario, Aluno alunora, Sala salanumSala) {
        this.codReserva = codReserva;
        this.data = data;
        this.horario = horario;
        this.alunora = alunora;
        this.salanumSala = salanumSala;
    }
    
    

    public Reserva(String codReserva) {
        this.codReserva = codReserva;
    }

    public Reserva(String codReserva, String data, String horario) {
        this.codReserva = codReserva;
        this.data = data;
        this.horario = horario;
    }

    public String getCodReserva() {
        return codReserva;
    }

    public void setCodReserva(String codReserva) {
        this.codReserva = codReserva;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Aluno getAlunora() {
        return alunora;
    }

    public void setAlunora(Aluno alunora) {
        this.alunora = alunora;
    }

    public Sala getSalanumSala() {
        return salanumSala;
    }

    public void setSalanumSala(Sala salanumSala) {
        this.salanumSala = salanumSala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codReserva != null ? codReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.codReserva == null && other.codReserva != null) || (this.codReserva != null && !this.codReserva.equals(other.codReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codReserva;
    }
    
}
