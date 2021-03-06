package project.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.entity.Addon;
import project.entity.Client;
import project.entity.Master;
import project.entity.Order;
import project.exceptions.NotFoundException;
import project.model.OrderModel;
import project.model.OrderSorted;
import project.repository.OrderRepository;

import javax.swing.text.DateFormatter;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {
    private MasterService masterService;
    private ClientService clientService;
    private AddonService addonService;
    private OrderRepository orderRepository;

    public Order addOrder(OrderModel orderModel) {
        Master master = masterService.getMasterById(orderModel.getMasterId());
        Client client = clientService.getClientById(orderModel.getClientId());
        List<Addon> addons = addonService.getAddonsByIds(orderModel.getAddonsIds());
        Order order = OrderModel.toOrder(orderModel);
        order.setMaster(master);
        order.setClient(client);
        order.setAddons(addons);
        return orderRepository.save(order);
    }

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

    public Order updateOrder(OrderModel orderModel) {
        Order orderUpd = getOrderById(orderModel.getId());
        orderUpd.setClient(orderModel.getClientId() == null ? orderUpd.getClient() : clientService.getClientById(orderModel.getClientId()));
        orderUpd.setMaster(orderModel.getMasterId() == null ? orderUpd.getMaster() : masterService.getMasterById(orderModel.getMasterId()));
        orderUpd.setAddons(orderModel.getAddonsIds().isEmpty() ? orderUpd.getAddons() : addonService.getAddonsByIds(orderModel.getAddonsIds()));
        orderUpd.setPrice(orderModel.getPrice() == null ? orderUpd.getPrice() : orderModel.getPrice());
        orderUpd.setStart(orderModel.getStart() == null ? orderUpd.getStart() : orderModel.getStart());
        orderUpd.setFinish(orderModel.getFinish() == null ? orderUpd.getFinish() : orderModel.getFinish());
        return orderRepository.save(orderUpd);
    }

    public void deleteOrder(Order order) {
        order.setClient(null);
        order.setMaster(null);
        orderRepository.delete(order);
    }

    public Iterable<OrderSorted> getOrdersByDate() {
        Collection<Order> ordersRaw = (Collection<Order>) orderRepository.findAll();
        List<OrderSorted> list = ordersRaw.stream()
                .filter(order -> order.getStart().isAfter(LocalDateTime.now()))
                .collect(Collectors.groupingBy(order -> order.getStart().getDayOfMonth()))
                .values().stream()
                .map(orders -> new OrderSorted(orders.get(0).getStart().format(DateTimeFormatter.ofPattern("dd MMMM", Locale.forLanguageTag("ru-RU"))), orders))
                .sorted(Comparator.comparing(os -> os.getOrders().get(0).getStart()))
                .toList();
        list.forEach(orderSorted -> orderSorted.getOrders().sort(Comparator.comparing(Order::getStart)));
        return list;
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
