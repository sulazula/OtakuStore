package pl.sulazula.Order_Service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.sulazula.Order_Service.entity.statuses.Status;

import java.sql.Timestamp;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "cant be null.")
    @Column(nullable = false)
    private Long userId;
    @NotNull(message = "cant be null.")
    @Column(nullable = false)
    private Long productId;
    @NotNull(message = "cant be null.")
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp orderDate;
    @UpdateTimestamp
    private Timestamp updateDate;
    @Enumerated(EnumType.STRING)
    private Status status;
}
