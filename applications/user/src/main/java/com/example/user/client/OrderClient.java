package com.example.user.client;

import com.example.user.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="application-order")
public interface OrderClient {

    @GetMapping("/application-order/{userId}/orders")
    List<ResponseOrder> getOrders(@PathVariable String userId);
}
