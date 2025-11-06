package pl.sulazula.Order_Service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sulazula.Order_Service.entity.Order;
import pl.sulazula.Order_Service.entity.statuses.Status;
import pl.sulazula.Order_Service.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService orderService) {
        this.service = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {

        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {

        return ResponseEntity.ok(service.findByUserId(userId));
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable String status) {

        Status st = Status.valueOf(status);
        return ResponseEntity.ok(service.findByStatus(st));
    }
}
