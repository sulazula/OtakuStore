package pl.sulazula.Order_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sulazula.Order_Service.entity.Order;
import pl.sulazula.Order_Service.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {

        return orderRepository.findAll();
    }

    public Order findById(Long id) {

        return orderRepository.findById(id).get();
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
