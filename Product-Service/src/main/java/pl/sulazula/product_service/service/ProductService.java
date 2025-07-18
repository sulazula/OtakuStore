package pl.sulazula.product_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sulazula.product_service.entity.Product;
import pl.sulazula.product_service.entity.types.Type;
import pl.sulazula.product_service.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository prepo;

    public List<Product> findAll() {

        return prepo.findAll();
    }

    public Product findById(Long id) {

        return prepo.findById(id).orElse(null);
    }

    public Product findByName(String name) {

        return prepo.findByName(name);
    }

    public List<Product> findByType(Type type) {

        return prepo.findByType(type);
    }

    public List<Product> findByAmountDesc() {
        List<Product> list = findAll();
        list.sort(Comparator.comparingInt(Product::getAmount));

        return list;
    }

    public Product save(Product product) {

        return prepo.save(product);
    }

    public boolean delete(Product product) {

        prepo.delete(product);
        return true;
    }

    public void decreaseAmount(Product product, int decreaseNumber) {

        product.setAmount(product.getAmount() - decreaseNumber);
    }
}
