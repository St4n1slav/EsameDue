package it.epicode.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cataloghi_bibliografici")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_catalogo", discriminatorType = DiscriminatorType.STRING)
public abstract class Catalogo {
    @Id
    private Integer codiceISBN;
    private String titolo;
    private int annoDiPubblicazione;
    private int numeroPagine;
    @OneToMany(mappedBy = "elementoPrestato")
    private List<Prestito> prestiti;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Catalogo catalogo = (Catalogo) o;
        return getCodiceISBN() != null && Objects.equals(getCodiceISBN(), catalogo.getCodiceISBN());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
