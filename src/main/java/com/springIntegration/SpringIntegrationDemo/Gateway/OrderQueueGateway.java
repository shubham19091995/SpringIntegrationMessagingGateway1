package com.springIntegration.SpringIntegrationDemo.Gateway;


import com.springIntegration.SpringIntegrationDemo.Model.Order;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface OrderQueueGateway {

    @Gateway(requestChannel = "request-queue-in-channel")
    public Message<Order> placeOrder(Order order);
}
