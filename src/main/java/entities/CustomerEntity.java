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
@Table(name = "customer", schema = "sakila")
public class CustomerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id", nullable = false)
    private short customerId;

    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    @OneToMany(mappedBy = "customer")
    private List<RentalEntity> rentals;
    @OneToMany(mappedBy = "customerId")  // mappedBy debe ser el nombre del atributo en PaymentEntity
    private List<PaymentEntity> payments;
    @Basic
    @Column(name = "active", nullable = false)
    private byte active;

    @Basic
    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;

    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return customerId == that.customerId ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
