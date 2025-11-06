package pl.sulazula.Order_Service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sulazula.Order_Service.entity.Order;
import pl.sulazula.Order_Service.entity.statuses.Status;
import pl.sulazula.Order_Service.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {

        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {

        return orderRepository.findById(id);
    }

    public List<Order> findByUserId(Long userId) {

        return orderRepository.findByUserId(userId);
    }

    public List<Order> findByStatus(Status status) {

        return orderRepository.findByStatus(status);
    }

    public Order save(Order order) {

        return orderRepository.save(order);
    }

    public boolean delete(Order order) {

        orderRepository.delete(order);
        return true;
    }

    public Order update(Order order) {

        return orderRepository.save(order);
    }
}
