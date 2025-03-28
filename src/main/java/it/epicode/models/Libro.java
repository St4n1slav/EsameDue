package it.epicode.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Libro")
public class Libro extends Catalogo {
    private String autore;
    public String genere;

    public Libro(Integer codiceISBN, String titolo, int annoDiPubblicazione, int numeroPagine, List<Prestito> prestiti, String autore, String genere) {
        super(codiceISBN, titolo, annoDiPubblicazione, numeroPagine, prestiti);
        this.autore = autore;
        this.genere = genere;
    }
}
