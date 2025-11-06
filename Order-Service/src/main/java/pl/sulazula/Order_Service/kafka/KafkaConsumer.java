package pl.sulazula.Order_Service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.sulazula.Order_Service.entity.OrderEvent;
import pl.sulazula.Order_Service.repository.OrderRepository;

@Slf4j
@Component
public class KafkaConsumer {

    private final OrderRepository orderRepository;

    public KafkaConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "order_events")
    public void listen(OrderEvent orderEvent) {
        log.info("Received order event: {}", orderEvent);
        
    }
}
