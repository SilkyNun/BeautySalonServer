package project.model;

import lombok.Data;
import project.entity.Order;

import java.util.Date;

@Data
public class OrderModel {
    private Long id;
    private Double price;
    private Date start;
    private Date finish;
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
