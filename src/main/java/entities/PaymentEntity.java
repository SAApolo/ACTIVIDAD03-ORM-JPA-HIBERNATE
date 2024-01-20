package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
@Getter
@Setter
@Entity
@Table(name = "payment", schema = "sakila")
public class PaymentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "payment_id", nullable = false)
    private short paymentId;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customerId;  // Debería coincidir con el nombre del atributo en CustomerEntity

    @Basic
    @Column(name = "staff_id", nullable = false)
    private byte staffId;
    @Basic
    @Column(name = "rental_id", nullable = true)
    private Integer rentalId;
    @Basic
    @Column(name = "amount", nullable = false, precision = 2)
    private BigDecimal amount;
    @Basic
    @Column(name = "payment_date", nullable = false)
    private Timestamp paymentDate;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEntity that = (PaymentEntity) o;
        return paymentId == that.paymentId ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId);
    }
}
