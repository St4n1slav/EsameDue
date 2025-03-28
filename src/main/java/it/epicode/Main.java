package it.epicode;

import it.epicode.dao.Archivio;
import it.epicode.dao.PrestitoDAO;
import it.epicode.dao.UtenteDAO;
import it.epicode.models.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo_bibliografico");
        EntityManager em = emf.createEntityManager();

        UtenteDAO utenteDAO = new UtenteDAO(em);
        Archivio archivio = new Archivio(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);

        Utente utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setDataDiNascita(LocalDate.of(1990, 1, 1));
        utente.setNumeroDiTessera(1);

        utenteDAO.save(utente);

        Libro libro = new Libro();
        libro.setCodiceISBN(1234);
        libro.setAnnoDiPubblicazione(2020);
        libro.setAutore("Autore 1");
        libro.setTitolo("Titolo 1");
        libro.setNumeroPagine(100);
        libro.setGenere("Genere Fantasy");

        archivio.saveCatalogo(libro);

        Rivista rivista = new Rivista();
        rivista.setCodiceISBN(5678);
        rivista.setAnnoDiPubblicazione(2021);
        rivista.setTitolo("Titolo 2");
        rivista.setPeriodicita(Periodicita.MENSILE);

        archivio.saveCatalogo(rivista);

        Catalogo catalogo = archivio.getByISBN(1234);
        System.out.println(catalogo);

        List<Catalogo> catalogos = archivio.getByAnnoDiPubblicazione(2020);
        catalogos.forEach(c -> System.out.println(c));

        List<Libro> libros = archivio.getByAutore("Autore 1");
        libros.forEach(c -> System.out.println(c));

        List<Catalogo> catalogos2 = archivio.getByTitolo("Titol");
        catalogos2.forEach(c -> System.out.println(c));

        Prestito prestito = new Prestito();
        prestito.setUtente(utente);
        prestito.setElementoPrestato(libro);
        prestito.setDataInizioPrestito(LocalDate.now());
        prestito.setDataRestituzionePrevista(LocalDate.now().plusDays(30));
        prestito.setId(1);

        prestitoDAO.save(prestito);

        List<Catalogo> cataloghiInPrestito = archivio.getByNumeroDiTessera(1);
        cataloghiInPrestito.forEach(c -> System.out.println(c));

        Prestito prestito2 = new Prestito();
        prestito2.setUtente(utente);
        prestito2.setElementoPrestato(libro);
        prestito2.setDataInizioPrestito(LocalDate.now());
        prestito2.setDataRestituzionePrevista(LocalDate.now().minusDays(30));
        prestito2.setId(2);

        prestitoDAO.save(prestito2);

        List<Prestito> cataloghiScaduti = archivio.getExpired();
        cataloghiScaduti.forEach(c -> System.out.println(c));

    }

}