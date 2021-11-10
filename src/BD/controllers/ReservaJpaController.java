/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.controllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BD.Entities.Sala;
import BD.Entities.Aluno;
import BD.Entities.Reserva;
import BD.Entities.ReservaPK;
import BD.controllers.exceptions.NonexistentEntityException;
import BD.controllers.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Samuel
 */
public class ReservaJpaController implements Serializable {

    public ReservaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reserva reserva) throws PreexistingEntityException, Exception {
        if (reserva.getReservaPK() == null) {
            reserva.setReservaPK(new ReservaPK());
        }
        if (reserva.getAlunoList() == null) {
            reserva.setAlunoList(new ArrayList<Aluno>());
        }
        reserva.getReservaPK().setSalanumSala(reserva.getSala().getNumSala());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sala sala = reserva.getSala();
            if (sala != null) {
                sala = em.getReference(sala.getClass(), sala.getNumSala());
                reserva.setSala(sala);
            }
            List<Aluno> attachedAlunoList = new ArrayList<Aluno>();
            for (Aluno alunoListAlunoToAttach : reserva.getAlunoList()) {
                alunoListAlunoToAttach = em.getReference(alunoListAlunoToAttach.getClass(), alunoListAlunoToAttach.getRa());
                attachedAlunoList.add(alunoListAlunoToAttach);
            }
            reserva.setAlunoList(attachedAlunoList);
            em.persist(reserva);
            if (sala != null) {
                sala.getReservaList().add(reserva);
                sala = em.merge(sala);
            }
            for (Aluno alunoListAluno : reserva.getAlunoList()) {
                alunoListAluno.getReservaList().add(reserva);
                alunoListAluno = em.merge(alunoListAluno);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReserva(reserva.getReservaPK()) != null) {
                throw new PreexistingEntityException("Reserva " + reserva + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reserva reserva) throws NonexistentEntityException, Exception {
        reserva.getReservaPK().setSalanumSala(reserva.getSala().getNumSala());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva persistentReserva = em.find(Reserva.class, reserva.getReservaPK());
            Sala salaOld = persistentReserva.getSala();
            Sala salaNew = reserva.getSala();
            List<Aluno> alunoListOld = persistentReserva.getAlunoList();
            List<Aluno> alunoListNew = reserva.getAlunoList();
            if (salaNew != null) {
                salaNew = em.getReference(salaNew.getClass(), salaNew.getNumSala());
                reserva.setSala(salaNew);
            }
            List<Aluno> attachedAlunoListNew = new ArrayList<Aluno>();
            for (Aluno alunoListNewAlunoToAttach : alunoListNew) {
                alunoListNewAlunoToAttach = em.getReference(alunoListNewAlunoToAttach.getClass(), alunoListNewAlunoToAttach.getRa());
                attachedAlunoListNew.add(alunoListNewAlunoToAttach);
            }
            alunoListNew = attachedAlunoListNew;
            reserva.setAlunoList(alunoListNew);
            reserva = em.merge(reserva);
            if (salaOld != null && !salaOld.equals(salaNew)) {
                salaOld.getReservaList().remove(reserva);
                salaOld = em.merge(salaOld);
            }
            if (salaNew != null && !salaNew.equals(salaOld)) {
                salaNew.getReservaList().add(reserva);
                salaNew = em.merge(salaNew);
            }
            for (Aluno alunoListOldAluno : alunoListOld) {
                if (!alunoListNew.contains(alunoListOldAluno)) {
                    alunoListOldAluno.getReservaList().remove(reserva);
                    alunoListOldAluno = em.merge(alunoListOldAluno);
                }
            }
            for (Aluno alunoListNewAluno : alunoListNew) {
                if (!alunoListOld.contains(alunoListNewAluno)) {
                    alunoListNewAluno.getReservaList().add(reserva);
                    alunoListNewAluno = em.merge(alunoListNewAluno);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ReservaPK id = reserva.getReservaPK();
                if (findReserva(id) == null) {
                    throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ReservaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva reserva;
            try {
                reserva = em.getReference(Reserva.class, id);
                reserva.getReservaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.", enfe);
            }
            Sala sala = reserva.getSala();
            if (sala != null) {
                sala.getReservaList().remove(reserva);
                sala = em.merge(sala);
            }
            List<Aluno> alunoList = reserva.getAlunoList();
            for (Aluno alunoListAluno : alunoList) {
                alunoListAluno.getReservaList().remove(reserva);
                alunoListAluno = em.merge(alunoListAluno);
            }
            em.remove(reserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reserva> findReservaEntities() {
        return findReservaEntities(true, -1, -1);
    }

    public List<Reserva> findReservaEntities(int maxResults, int firstResult) {
        return findReservaEntities(false, maxResults, firstResult);
    }

    private List<Reserva> findReservaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reserva.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Reserva findReserva(ReservaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reserva> rt = cq.from(Reserva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
