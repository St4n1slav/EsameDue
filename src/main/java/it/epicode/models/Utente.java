package it.epicode.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Utente {
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    @Id
    private Integer numeroDiTessera;
    @OneToMany(mappedBy = "utente")
    private List<Prestito> prestiti;
}
