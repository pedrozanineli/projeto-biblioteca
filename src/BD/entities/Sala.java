/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Samuel
 */
@Entity
@Table(name = "sala")
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s"),
    @NamedQuery(name = "Sala.findByNumSala", query = "SELECT s FROM Sala s WHERE s.numSala = :numSala"),
    @NamedQuery(name = "Sala.findByLotacao", query = "SELECT s FROM Sala s WHERE s.lotacao = :lotacao"),
    @NamedQuery(name = "Sala.findByLousa", query = "SELECT s FROM Sala s WHERE s.lousa = :lousa")})
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numSala")
    private Integer numSala;
    @Basic(optional = false)
    @Column(name = "lotacao")
    private String lotacao;
    @Basic(optional = false)
    @Column(name = "lousa")
    private String lousa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sala")
    private List<Reserva> reservaList;

    public Sala() {
    }

    public Sala(Integer numSala) {
        this.numSala = numSala;
    }

    public Sala(Integer numSala, String lotacao, String lousa) {
        this.numSala = numSala;
        this.lotacao = lotacao;
        this.lousa = lousa;
    }

    public Integer getNumSala() {
        return numSala;
    }

    public void setNumSala(Integer numSala) {
        this.numSala = numSala;
    }

    public String getLotacao() {
        return lotacao;
    }

    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
    }

    public String getLousa() {
        return lousa;
    }

    public void setLousa(String lousa) {
        this.lousa = lousa;
    }

    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numSala != null ? numSala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.numSala == null && other.numSala != null) || (this.numSala != null && !this.numSala.equals(other.numSala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numSala.toString();
    }
    
}
