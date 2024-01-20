package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "film", schema = "sakila")
public class FilmEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "film_id", nullable = false)
    private short filmId;

    @Basic
    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;

    @Basic
    @Column(name = "release_year", nullable = true)
    private Object releaseYear;

    @Basic
    @Column(name = "rental_duration", nullable = false)
    private byte rentalDuration;

    @Basic
    @Column(name = "rental_rate", nullable = false, precision = 2)
    private BigDecimal rentalRate;

    @Basic
    @Column(name = "length", nullable = true)
    private Short length;

    @Basic
    @Column(name = "replacement_cost", nullable = false, precision = 2)
    private BigDecimal replacementCost;

    @Basic
    @Column(name = "rating", nullable = true)
    private Object rating;

    @Basic
    @Column(name = "special_features", nullable = true)
    private Object specialFeatures;

    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @ManyToMany(mappedBy = "films")
    private List<ActorEntity> actors;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private LanguageEntity language;

    @ManyToOne
    @JoinColumn(name = "original_language_id", nullable = true)
    private LanguageEntity originalLanguage;

    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryEntity> categories;

    // Método para obtener las copias de la película desde el inventario
    public List<InventoryEntity> getInventories(EntityManager em) {
        TypedQuery<InventoryEntity> query = em.createQuery(
                "SELECT i FROM InventoryEntity i WHERE i.film.filmId = :filmId", InventoryEntity.class);
        query.setParameter("filmId", this.filmId);
        return query.getResultList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmEntity that = (FilmEntity) o;
        return filmId == that.filmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId);
    }
}
