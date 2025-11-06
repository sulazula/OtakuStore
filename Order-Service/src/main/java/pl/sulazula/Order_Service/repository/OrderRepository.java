package pl.sulazula.Order_Service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sulazula.Order_Service.entity.Order;
import pl.sulazula.Order_Service.entity.statuses.Status;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findByStatus(Status status);
}