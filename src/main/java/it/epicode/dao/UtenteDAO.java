package it.epicode.dao;


import it.epicode.models.Utente;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UtenteDAO {

    private final EntityManager em;

    public void save(Utente utente) {
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
    }

    public void delete(Integer id) {
        em.getTransaction().begin();
        Utente utente = em.find(Utente.class, id);
        if (utente != null) {
            em.remove(utente);
        }
        em.getTransaction().commit();

    }
}
