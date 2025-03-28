package it.epicode.dao;


import it.epicode.models.Prestito;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PrestitoDAO {

    private final EntityManager em;

    public void save(Prestito prestito) {
        em.getTransaction().begin();
        em.persist(prestito);
        em.getTransaction().commit();
    }

    public void delete(Integer id) {
        em.getTransaction().begin();
        Prestito prestito = em.find(Prestito.class, id);
        if (prestito != null) {
            em.remove(prestito);
        }
        em.getTransaction().commit();

    }
}
