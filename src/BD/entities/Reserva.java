/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Reserva.findByCodReserva", query = "SELECT r FROM Reserva r WHERE r.reservaPK.codReserva = :codReserva"),
    @NamedQuery(name = "Reserva.findByData", query = "SELECT r FROM Reserva r WHERE r.data = :data"),
    @NamedQuery(name = "Reserva.findByHorario", query = "SELECT r FROM Reserva r WHERE r.horario = :horario"),
    @NamedQuery(name = "Reserva.findBySalanumSala", query = "SELECT r FROM Reserva r WHERE r.reservaPK.salanumSala = :salanumSala")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservaPK reservaPK;
    @Basic(optional = false)
    
    @Column(name = "Data")
    private String data;
    @Basic(optional = false)
    @Column(name = "Horario")
    private String horario;
    @ManyToMany(mappedBy = "reservaList")
    private List<Aluno> alunoList;
    @JoinColumn(name = "Sala_numSala", referencedColumnName = "numSala", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sala sala;

    public Reserva() {
    }

    public Reserva(ReservaPK reservaPK) {
        this.reservaPK = reservaPK;
    }

    public Reserva(ReservaPK reservaPK, String data, String horario, List<Aluno> alunoList, Sala sala) {
        this.reservaPK = reservaPK;
        this.data = data;
        this.horario = horario;
        this.alunoList = alunoList;
        this.sala = sala;
    }

    


    public Reserva(ReservaPK reservaPK, String data, String horario) {
        this.reservaPK = reservaPK;
        this.data = data;
        this.horario = horario;
    }

    public Reserva(String codReserva, int salanumSala) {
        this.reservaPK = new ReservaPK(codReserva, salanumSala);
    }

    public ReservaPK getReservaPK() {
        return reservaPK;
    }

    public void setReservaPK(ReservaPK reservaPK) {
        this.reservaPK = reservaPK;
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

    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public void setAlunoList(List<Aluno> alunoList) {
        this.alunoList = alunoList;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservaPK != null ? reservaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.reservaPK == null && other.reservaPK != null) || (this.reservaPK != null && !this.reservaPK.equals(other.reservaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BD.Entities.Reserva[ reservaPK=" + reservaPK + " ]";
    }
    
}
