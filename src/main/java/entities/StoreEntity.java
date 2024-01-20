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
@Table(name = "store", schema = "sakila")
public class StoreEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id", nullable = false)
    private byte storeId;

    @ManyToOne(fetch = FetchType.LAZY) // Configuración de lazy loading
    @JoinColumn(name = "manager_staff_id", nullable = false, insertable = false, updatable = false)
    private StaffEntity manager;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY) // Configuración de lazy loading
    private List<StaffEntity> staffList;
    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @Basic
    @Column(name = "manager_staff_id", nullable = false)
    private byte managerStaffId;

    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreEntity that = (StoreEntity) o;
        return storeId == that.storeId ;
    }
    @Override
    public int hashCode() {
        return Objects.hash(storeId);
    }
}