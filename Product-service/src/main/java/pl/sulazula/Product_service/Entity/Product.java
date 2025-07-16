package pl.sulazula.Product_service.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import pl.sulazula.Product_service.Converter.JsonConverter;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String description;
    private String anime_name;
    private Integer amount;
    private BigDecimal price;
    private String imageUrl;

    @Column(columnDefinition = "jsonb")
    @Convert(converter = JsonConverter.class)
    private String attributes;

    public Product() {
    }

}
