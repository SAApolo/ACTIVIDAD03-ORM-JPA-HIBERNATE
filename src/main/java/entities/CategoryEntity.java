package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;
@Getter
@Setter
@Entity
@Table(name = "category", schema = "sakila")
public class CategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id", nullable = false)
    private byte categoryId;
    @Basic
    @Column(name = "name", nullable = false, length = 25)
    private String name;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }
}
