package project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime finish;
    @ManyToOne
    @JoinColumn(name = "master_id")
//    @JsonIgnore
    private Master master;
    @ManyToOne
    @JoinColumn(name = "client_id")
//    @JsonIgnore
    private Client client;


    public Order(Double price, LocalDateTime start, LocalDateTime finish, Master master, Client client) {
        this.price = price;
        this.start = start;
        this.finish = finish;
        this.master = master;
        this.client = client;
    }
//
//    @JsonGetter("masterId")
//    public Long getMasterId() {
//        return master.getId();
//    }
//
//    @JsonGetter("clientId")
//    public Long getClientId() {
//        return client.getId();
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(price, order.price) && Objects.equals(start, order.start) && Objects.equals(finish, order.finish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, start, finish);
    }
}
