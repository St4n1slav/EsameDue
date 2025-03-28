package it.epicode.dao;

import it.epicode.models.Catalogo;
import it.epicode.models.Libro;
import it.epicode.models.Prestito;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class Archivio {
    private final EntityManager em;

    public void saveCatalogo(Catalogo catalogo) {
        em.getTransaction().begin();
        em.persist(catalogo);
        em.getTransaction().commit();
    }

    public void deleteCatalogo(Integer id) {
        em.getTransaction().begin();
        Catalogo catalogo = em.find(Catalogo.class, id);
        if (catalogo != null) {
            em.remove(catalogo);
        }
        em.getTransaction().commit();

    }

    public Catalogo getByISBN(Integer isbn) {
        return em.createQuery("SELECT c FROM Catalogo c where c.codiceISBN = :isbn", Catalogo.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
    }

    public List<Catalogo> getByAnnoDiPubblicazione(Integer annoDiPubblicazione) {
        return em.createQuery("SELECT c FROM Catalogo c where c.annoDiPubblicazione = :annoDiPubblicazione", Catalogo.class)
                .setParameter("annoDiPubblicazione", annoDiPubblicazione)
                .getResultList();
    }

    public List<Libro> getByAutore(String autore) {
        return em.createQuery("SELECT c FROM Libro c where c.autore = :autore", Libro.class)
                .setParameter("autore", autore)
                .getResultList();
    }

    public List<Catalogo> getByTitolo(String titolo) {
        return em.createQuery("SELECT c FROM Catalogo c where c.titolo like :titolo", Catalogo.class)
                .setParameter("titolo", "%" + titolo + "%")
                .getResultList();
    }

    public List<Catalogo> getByNumeroDiTessera(Integer numeroDiTessera) {
        return em.createQuery("""
                        SELECT c FROM Catalogo c
                        JOIN Prestito p ON c.codiceISBN = p.elementoPrestato.codiceISBN
                        WHERE p.utente.numeroDiTessera = :numeroDiTessera
                        AND p.dataRestituzioneEffetiva IS NULL
                        AND p.dataRestituzionePrevista < :currentDate
                        """, Catalogo.class)
                .setParameter("numeroDiTessera", numeroDiTessera)
                .setParameter("currentDate", LocalDate.now())
                .getResultList();
    }

    public List<Prestito> getExpired() {
        return em.createQuery("""
                        SELECT p FROM Prestito p
                        WHERE p.dataRestituzioneEffetiva IS NULL
                        AND p.dataRestituzionePrevista < :currentDate
                        """, Prestito.class)
                .setParameter("currentDate", LocalDate.now())
                .getResultList();
    }

}
