/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Samuel
 */
@Embeddable
public class ReservaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "codReserva")
    private String codReserva;
    @Basic(optional = false)
    @Column(name = "Sala_numSala")
    private int salanumSala;

    public ReservaPK() {
    }

    public ReservaPK(String codReserva, int salanumSala) {
        this.codReserva = codReserva;
        this.salanumSala = salanumSala;
    }

    public String getCodReserva() {
        return codReserva;
    }

    public void setCodReserva(String codReserva) {
        this.codReserva = codReserva;
    }

    public int getSalanumSala() {
        return salanumSala;
    }

    public void setSalanumSala(int salanumSala) {
        this.salanumSala = salanumSala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codReserva != null ? codReserva.hashCode() : 0);
        hash += (int) salanumSala;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaPK)) {
            return false;
        }
        ReservaPK other = (ReservaPK) object;
        if ((this.codReserva == null && other.codReserva != null) || (this.codReserva != null && !this.codReserva.equals(other.codReserva))) {
            return false;
        }
        if (this.salanumSala != other.salanumSala) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codReserva;
    }
    
}
