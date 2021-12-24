package project.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private Boolean admin;
    @ManyToOne
    @JoinColumn(name = "master_id")
    @JsonIgnore
    private Master master;

    @JsonGetter("masterId")
    public Long getMasterId() {
       return master.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(username, account.username) && Objects.equals(password, account.password) && Objects.equals(admin, account.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, admin);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }
}
