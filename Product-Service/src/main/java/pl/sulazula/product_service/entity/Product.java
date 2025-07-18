package pl.sulazula.product_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.sulazula.product_service.converter.JsonConverter;
import pl.sulazula.product_service.entity.types.Type;

import java.math.BigDecimal;
import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotNull(message = "name is required.")
    private String name;
    private String description;
    private String anime_name;
    @NotNull(message = "amount is required.")
    private Integer amount;
    @NotNull(message = "type is required.")
    @Enumerated(EnumType.STRING)
    private Type type;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "must be positive")
    private BigDecimal price;
    private String imageUrl;

    @Column(columnDefinition = "jsonb")
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> attributes;

    public Product() {
    }

}
