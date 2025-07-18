package pl.sulazula.Order_Service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sulazula.Order_Service.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}