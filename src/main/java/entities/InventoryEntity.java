package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "inventory", schema = "sakila")
public class InventoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "inventory_id", nullable = false)
    private short inventoryId;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private FilmEntity film;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<RentalEntity> rentals;

    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryEntity that = (InventoryEntity) o;
        return inventoryId == that.inventoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId);
    }
}
