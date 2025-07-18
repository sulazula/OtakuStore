package pl.sulazula.product_service.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sulazula.product_service.entity.Product;
import pl.sulazula.product_service.entity.types.Type;
import pl.sulazula.product_service.service.KafkaProducer;
import pl.sulazula.product_service.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;
    private final KafkaProducer kafkaProducer;

    public ProductController(ProductService service, KafkaProducer kafkaProducer) {
        this.service = service;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> list = service.findAll();
        return list.isEmpty() ?
                ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        Product product = service.findById(id);
        return product != null ?
                ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {

        Product product = service.findByName(name);
        return product != null ?
                ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Product>> getProductByType(@PathVariable String type) {

        Type enumType = Type.valueOf(type.toUpperCase());
        List<Product> list = service.findByType(enumType);
        return list.isEmpty() ?
                ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {

        Product createdProduct = service.save(product);
        kafkaProducer.sendCreated(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PatchMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {

        Product updatedProduct = service.save(product);
        kafkaProducer.sendUpdated(updatedProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {

        Product product = service.findById(id);
        boolean deleted = service.delete(product);
        kafkaProducer.sendDeleted(product);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/decrease/{id}/{amount}")
    public ResponseEntity<?> decreaseProduct(@PathVariable Long id, @PathVariable int amount) {

        Product product = service.findById(id);
        if (product == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found.");

        int actual = product.getAmount();
        if (actual < amount) return ResponseEntity.status(HttpStatus.CONFLICT).body("Not enough items");

        service.decreaseAmount(product, amount);
        actual -= amount;
        kafkaProducer.sendUpdated(product);

        if (actual == 0) return ResponseEntity.status(HttpStatus.OK).body("No items left");
        return ResponseEntity.ok(product);
    }

}
