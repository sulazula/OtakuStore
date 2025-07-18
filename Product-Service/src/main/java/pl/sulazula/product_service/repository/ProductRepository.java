package pl.sulazula.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sulazula.product_service.entity.Product;
import pl.sulazula.product_service.entity.types.Type;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    List<Product> findByType(Type type);
}
