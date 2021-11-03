/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.controllers;

import BD.controllers.exceptions.NonexistentEntityException;
import BD.controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BD.entities.Aluno;
import BD.entities.Reserva;
import BD.entities.Sala;
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aluno alunora = reserva.getAlunora();
            if (alunora != null) {
                alunora = em.getReference(alunora.getClass(), alunora.getRa());
                reserva.setAlunora(alunora);
            }
            Sala salanumSala = reserva.getSalanumSala();
            if (salanumSala != null) {
                salanumSala = em.getReference(salanumSala.getClass(), salanumSala.getNumSala());
                reserva.setSalanumSala(salanumSala);
            }
            em.persist(reserva);
            if (alunora != null) {
                alunora.getReservaList().add(reserva);
                alunora = em.merge(alunora);
            }
            if (salanumSala != null) {
                salanumSala.getReservaList().add(reserva);
                salanumSala = em.merge(salanumSala);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReserva(reserva.getCodReserva()) != null) {
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva persistentReserva = em.find(Reserva.class, reserva.getCodReserva());
            Aluno alunoraOld = persistentReserva.getAlunora();
            Aluno alunoraNew = reserva.getAlunora();
            Sala salanumSalaOld = persistentReserva.getSalanumSala();
            Sala salanumSalaNew = reserva.getSalanumSala();
            if (alunoraNew != null) {
                alunoraNew = em.getReference(alunoraNew.getClass(), alunoraNew.getRa());
                reserva.setAlunora(alunoraNew);
            }
            if (salanumSalaNew != null) {
                salanumSalaNew = em.getReference(salanumSalaNew.getClass(), salanumSalaNew.getNumSala());
                reserva.setSalanumSala(salanumSalaNew);
            }
            reserva = em.merge(reserva);
            if (alunoraOld != null && !alunoraOld.equals(alunoraNew)) {
                alunoraOld.getReservaList().remove(reserva);
                alunoraOld = em.merge(alunoraOld);
            }
            if (alunoraNew != null && !alunoraNew.equals(alunoraOld)) {
                alunoraNew.getReservaList().add(reserva);
                alunoraNew = em.merge(alunoraNew);
            }
            if (salanumSalaOld != null && !salanumSalaOld.equals(salanumSalaNew)) {
                salanumSalaOld.getReservaList().remove(reserva);
                salanumSalaOld = em.merge(salanumSalaOld);
            }
            if (salanumSalaNew != null && !salanumSalaNew.equals(salanumSalaOld)) {
                salanumSalaNew.getReservaList().add(reserva);
                salanumSalaNew = em.merge(salanumSalaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = reserva.getCodReserva();
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

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva reserva;
            try {
                reserva = em.getReference(Reserva.class, id);
                reserva.getCodReserva();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.", enfe);
            }
            Aluno alunora = reserva.getAlunora();
            if (alunora != null) {
                alunora.getReservaList().remove(reserva);
                alunora = em.merge(alunora);
            }
            Sala salanumSala = reserva.getSalanumSala();
            if (salanumSala != null) {
                salanumSala.getReservaList().remove(reserva);
                salanumSala = em.merge(salanumSala);
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

    public Reserva findReserva(String id) {
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
