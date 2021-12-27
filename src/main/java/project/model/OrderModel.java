package project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import project.entity.Order;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OrderModel {
    private Long id;
    private Double price;
    private LocalDateTime start;
    private LocalDateTime finish;
    private Long masterId;
    private Long clientId;

    public static Order toOrder(OrderModel orderModel) {
        Order order = new Order();
        order.setId(orderModel.getId());
        order.setPrice(orderModel.getPrice());
        order.setStart(orderModel.getStart());
        order.setFinish(orderModel.getFinish());
        return order;
    }
}
