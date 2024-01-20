package entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;
@Getter
@Setter

@Entity
@Table(name = "city", schema = "sakila")
public class CityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "city_id", nullable = false)
    private short cityId;

    @Basic
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;

    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityEntity that = (CityEntity) o;
        return cityId == that.cityId ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId);
    }
}