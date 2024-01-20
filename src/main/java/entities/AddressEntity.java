package entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;
@Getter
@Setter

@Entity
@Table(name = "address", schema = "sakila")
public class AddressEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "address_id", nullable = false)
    private short addressId;

    @Basic
    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Basic
    @Column(name = "address2", nullable = true, length = 50)
    private String address2;

    @Basic
    @Column(name = "district", nullable = false, length = 20)
    private String district;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity city;

    @Basic
    @Column(name = "postal_code", nullable = true, length = 10)
    private String postalCode;

    @Basic
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return addressId == that.addressId ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId);
    }
}
