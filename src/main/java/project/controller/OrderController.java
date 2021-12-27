package project.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.Order;
import project.exceptions.NotFoundException;
import project.model.OrderModel;
import project.model.OrderSorted;
import project.service.OrderService;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Iterable<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("sorted")
    public ResponseEntity<Iterable<OrderSorted>> getSortedOrders() {
        return new ResponseEntity<>(orderService.getOrdersByDate(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody OrderModel orderModel) {
        return new ResponseEntity<>(orderService.addOrder(orderModel), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Order> updateOrder(@RequestBody OrderModel order) {
        return new ResponseEntity<>(orderService.updateOrder(order), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOrder(@RequestBody Order order) {
        orderService.deleteOrder(order);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException notFoundException) {
        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
