package project.repository;

import org.springframework.data.repository.CrudRepository;
import project.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
