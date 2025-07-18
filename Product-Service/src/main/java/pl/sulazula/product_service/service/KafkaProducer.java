package pl.sulazula.product_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.sulazula.product_service.entity.Product;

@Service
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC = "product_events";

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCreated(Product product) {

        log.info("Product created. Sent message to: " + TOPIC);
        kafkaTemplate.send(TOPIC, "PRODUCT_CREATED", serialize(product));
    }

    public void sendUpdated(Product product) {
        log.info("Product updated. Sent message to: " + TOPIC);
        kafkaTemplate.send(TOPIC, "PRODUCT_UPDATED", serialize(product));
    }

    public void sendDeleted(Product product) {
        log.info("Product deleted. Sent message to: " + TOPIC);
        kafkaTemplate.send(TOPIC, "PRODUCT_DELETED", serialize(product));
    }

    private String serialize(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
