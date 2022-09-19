package com.example.order.controller;

import com.example.order.dto.OrderDto;
import com.example.order.jpa.OrderEntity;
import com.example.order.messagequeue.KafkaProducer;
import com.example.order.messagequeue.OrderProducer;
import com.example.order.service.OrderService;
import com.example.order.vo.RequestOrder;
import com.example.order.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/application-order")
public class OrderController {
    Environment env;
    OrderService orderService;
    KafkaProducer kafkaProducer;
    OrderProducer orderProducer;

    @Autowired
    public OrderController(Environment env, OrderService orderService, KafkaProducer kafkaProducer, OrderProducer orderProducer) {
        this.env = env;
        this.orderService = orderService;
        this.kafkaProducer = kafkaProducer;
        this.orderProducer = orderProducer;
    }

    @RequestMapping("/check_test")
    public String status() {
        return String.format("Order PORT : %s", env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity createOrder(@PathVariable("userId") String userId,
                                      @RequestBody RequestOrder requestOrder) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = mapper.map(requestOrder, OrderDto.class);
        orderDto.setUserId(userId);

        // jpa
        OrderDto createdOrder = orderService.createOrder(orderDto);
        ResponseOrder responseOrder = mapper.map(createdOrder, ResponseOrder.class);

        // kafka
        //orderDto.setOrderId(UUID.randomUUID().toString());
        //orderDto.setTotalPrice(requestOrder.getQuantity() * requestOrder.getUnitPrice());

        // send this order to kafka
        kafkaProducer.send("example-catalog-topic", orderDto);
        //orderProducer.send("orders", orderDto);

        //ResponseOrder responseOrder = mapper.map(orderDto, ResponseOrder.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> createOrder(@PathVariable("userId") String userId) {
        Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(value -> {
            result.add(new ModelMapper().map(value, ResponseOrder.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
