package pl.sulazula.product_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.sulazula.product_service.entity.Product;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "product_events", groupId = "otakuProducts")
    public void listen(String message) {
        log.info("Received Message: {}", message);
    }

    @KafkaListener(topics = "order_events", groupId = "otakuProducts")
    public void listenOrders(Product product) {
        log.info("Received Order: " + product);

    }
}
