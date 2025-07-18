package pl.sulazula.product_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sulazula.product_service.entity.Product;
import pl.sulazula.product_service.entity.types.Type;
import pl.sulazula.product_service.service.ProductService;

import java.math.BigDecimal;

@SpringBootTest
class ProductServiceApplicationTests {

	@Autowired
	private ProductService productService;

	@Test
	void contextLoads() {

	}

	@Test
	void test1() {
		Product product = new Product();
		product.setName("Cosplay");
		product.setAmount(5);
		product.setPrice(BigDecimal.valueOf(150));
		product.setType(Type.COSPLAY);
		productService.save(product);
	}
}
