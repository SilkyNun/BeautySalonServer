package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name", "price"})
@ToString(of = {"id", "name", "price"})
@Table(name = "addon")
public class Addon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @DecimalMin(value = "0.00")
    private Double price;
    @JsonIgnore
    @ManyToMany(mappedBy = "addons")
    private List<Order> orders;
}
