package it.epicode.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Prestito {
    @Id
    private Integer id;
    @ManyToOne
    private Utente utente;
    @ManyToOne
    private Catalogo elementoPrestato;
    private LocalDate dataInizioPrestito = LocalDate.now();
    private LocalDate dataRestituzionePrevista = LocalDate.now().plusDays(30);
    private LocalDate dataRestituzioneEffetiva;


}
