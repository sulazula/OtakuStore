package pl.sulazula.Order_Service.entity;

import lombok.Data;
import pl.sulazula.Order_Service.entity.statuses.Status;

import java.util.List;

@Data
public class OrderEvent {
    private Long id;
    private Long userId;
    private List<Long> productIds;
    private String status;
}
