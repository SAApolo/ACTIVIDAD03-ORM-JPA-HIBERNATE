package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;
@Getter
@Setter
@Entity
@Table(name = "country", schema = "sakila")
public class CountryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "country_id", nullable = false)
    private short countryId;
    @Basic
    @Column(name = "country", nullable = false, length = 50)
    private String country;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity that = (CountryEntity) o;
        return countryId == that.countryId ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryId);
    }
}
