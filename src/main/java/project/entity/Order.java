package project.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
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
    private Date start;
    private Date finish;
    @ManyToOne
    @JoinColumn(name = "master_id")
    @JsonIgnore
    private Master master;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;


    public Order(Double price, Date start, Date finish, Master master, Client client) {
        this.price = price;
        this.start = start;
        this.finish = finish;
        this.master = master;
        this.client = client;
    }

    @JsonGetter("masterId")
    public Long getMasterId() {
        return master.getId();
    }

    @JsonGetter("clientId")
    public Long getClientId() {
        return client.getId();
    }

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