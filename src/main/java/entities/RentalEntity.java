package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "rental", schema = "sakila")
public class RentalEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rental_id", nullable = false)
    private int rentalId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id", nullable = false, insertable = false, updatable = false)
    private StaffEntity staff;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", nullable = false, insertable = false, updatable = false)
    private InventoryEntity inventory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false, insertable = false, updatable = false)
    private CustomerEntity customer;

    // constructor sin argumentos
    public RentalEntity() {
    }

    // constructor
    public RentalEntity(StaffEntity staff, InventoryEntity inventory,CustomerEntity customer, Timestamp rentalDate, Timestamp returnDate, Timestamp lastUpdate) {
        this.staff = staff;
        this.inventory=inventory;
        this.customer = customer;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.lastUpdate = lastUpdate;
    }

    @Basic
    @Column(name = "rental_date", nullable = false)
    private Timestamp rentalDate;

    @Basic
    @Column(name = "return_date", nullable = true)
    private Timestamp returnDate;

    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalEntity that = (RentalEntity) o;
        return rentalId == that.rentalId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalId);
    }
}
