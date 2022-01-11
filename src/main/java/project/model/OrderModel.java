package project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import project.entity.Addon;
import project.entity.Order;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderModel {
    private Long id;
    @NotNull
    @DecimalMin(value = "0.00")
    private Double price;
    @NotBlank
    private String area;
    @NotNull
    private LocalDateTime start;
    @NotNull
    @FutureOrPresent
    private LocalDateTime finish;
    @NotNull
    private Long masterId;
    @NotNull
    private Long clientId;
    @JsonProperty("addons")
    private List<Long> addonsIds = new ArrayList<>();

    public static Order toOrder(OrderModel orderModel) {
        Order order = new Order();
        order.setId(orderModel.getId());
        order.setPrice(orderModel.getPrice());
        order.setStart(orderModel.getStart());
        order.setFinish(orderModel.getFinish());
        order.setArea(orderModel.getArea());
        return order;
    }
}
