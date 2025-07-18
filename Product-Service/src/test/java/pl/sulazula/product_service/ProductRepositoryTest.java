package pl.sulazula.product_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.sulazula.product_service.entity.Product;
import pl.sulazula.product_service.entity.types.Type;
import pl.sulazula.product_service.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    void testSaveAndLoadProductWithAttributes() {
        // Создаём продукт с JSON-атрибутами
        Product product = new Product();
        product.setName("Uzumaki Naruto Figure");
        product.setAmount(5);
        product.setType(Type.FIGURINE);
        product.setPrice(BigDecimal.valueOf(149.99));

        Map<String, Object> attrs = new HashMap<>();
        attrs.put("height", 20);
        attrs.put("material", "PVC");
        attrs.put("anime", "Naruto");
        product.setAttributes(attrs);

        // Сохраняем
        Product saved = repository.save(product);

        // Загружаем
        Product loaded = repository.findById(saved.getId()).orElse(null);

        assertNotNull(loaded);
        assertEquals("Uzumaki Naruto Figure", loaded.getName());
        assertNotNull(loaded.getAttributes());
        assertEquals("PVC", loaded.getAttributes().get("material"));
        assertEquals(20, loaded.getAttributes().get("height"));
    }
}
