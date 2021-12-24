package project.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.Order;
import project.exceptions.NotFoundException;
import project.service.OrderService;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Iterable<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{masterId}")
    public ResponseEntity<Order> addOrder(@RequestBody Order order, @PathVariable("masterId") Long masterId) {
        return new ResponseEntity<>(orderService.addOrder(masterId, order), HttpStatus.CREATED);
    }
}
