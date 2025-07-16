package pl.sulazula.Product_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sulazula.Product_service.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
