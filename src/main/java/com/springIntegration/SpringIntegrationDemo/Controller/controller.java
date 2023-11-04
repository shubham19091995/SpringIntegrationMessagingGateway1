package com.springIntegration.SpringIntegrationDemo.Controller;

import com.springIntegration.SpringIntegrationDemo.Gateway.OrderGateway;
import com.springIntegration.SpringIntegrationDemo.Gateway.OrderQueueGateway;
import com.springIntegration.SpringIntegrationDemo.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {


    @Autowired
    private OrderGateway orderGateway;

    @Autowired
    OrderQueueGateway orderQueueGateway;

    @PostMapping("/placeOrder")
    public Order placeOrder(@RequestBody Order order){
        return orderGateway.placeOrder(order).getPayload();
    }

    @PostMapping("/placeOrderQueue")
    public Order placeOrderQueue(@RequestBody Order order){
        return orderQueueGateway.placeOrder(order).getPayload();
    }
}
