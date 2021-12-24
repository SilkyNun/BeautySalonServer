package project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String tel;
    private Double percent;
    @OneToMany(mappedBy = "master", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Account> accounts = new HashSet<>();
    @OneToMany(mappedBy = "master", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();


    public Master(String name, String tel, double percent) {
        this.name = name;
        this.tel = tel;
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Master master = (Master) o;
        return Double.compare(master.percent, percent) == 0 && Objects.equals(id, master.id) && Objects.equals(name, master.name) && Objects.equals(tel, master.tel) && Objects.equals(accounts, master.accounts) && Objects.equals(orders, master.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tel, percent, accounts, orders);
    }

    @Override
    public String toString() {
        return "Master{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", percent=" + percent +
                ", accounts=" + accounts +
                ", orders=" + orders +
                '}';
    }
}
