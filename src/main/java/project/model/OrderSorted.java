package project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.entity.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class OrderSorted {
    private String title;
    private List<Order> orders = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderSorted that = (OrderSorted) o;
        return Objects.equals(title, that.title) && Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, orders);
    }

    @Override
    public String toString() {
        return "OrderSorted{" +
                "title='" + title + '\'' +
                ", orders=" + orders +
                '}';
    }
}
