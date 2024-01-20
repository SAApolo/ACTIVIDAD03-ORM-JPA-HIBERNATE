package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;
@Getter
@Setter
@Entity
@Table(name = "language", schema = "sakila")
public class LanguageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "language_id", nullable = false)
    private byte languageId;
    @Basic
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguageEntity that = (LanguageEntity) o;
        return languageId == that.languageId ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId);
    }
}
