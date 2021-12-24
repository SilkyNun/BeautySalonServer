package project.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.entity.Master;
import project.entity.Order;
import project.exceptions.NotFoundException;
import project.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {
    private MasterService masterService;
    private OrderRepository orderRepository;

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) throws NotFoundException {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new NotFoundException("Order not found");
        }
        return order.get();
    }

    public Order addOrder(Long masterID, Order order) {
        Master master = masterService.getMasterById(masterID);
        order.setMaster(master);
        return orderRepository.save(order);
    }
}
