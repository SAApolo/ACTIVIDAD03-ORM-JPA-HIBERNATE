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
@Table(name = "staff", schema = "sakila")
public class StaffEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "staff_id", nullable = false)
    private byte staffId;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false, insertable = false, updatable = false)
    private StoreEntity store;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false, insertable = false, updatable = false)
    private AddressEntity address;

    @OneToMany(mappedBy = "staff")
    private List<RentalEntity> rentals;

    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Basic
    @Column(name = "address_id", nullable = false)
    private short addressId;

    @Basic
    @Column(name = "picture", nullable = true)
    private byte[] picture;

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @Basic
    @Column(name = "store_id", nullable = false)
    private byte storeId;

    @Basic
    @Column(name = "active", nullable = false)
    private byte active;

    @Basic
    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Basic
    @Column(name = "password", nullable = true, length = 40)
    private String password;

    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffEntity that = (StaffEntity) o;
        return staffId == that.staffId ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId);
    }
}