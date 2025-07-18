package pl.sulazula.product_service.entity.types;

import jakarta.persistence.Enumerated;
import org.springframework.stereotype.Component;

public enum Type {
    FIGURINE,
    CLOTHING,
    ACCESSORY,
    COSPLAY
}
