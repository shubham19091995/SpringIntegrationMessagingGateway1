package com.springIntegration.SpringIntegrationDemo.Service;


import com.springIntegration.SpringIntegrationDemo.Model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class OrderQueueService {


    @Bean("order-queue-process-channel")
    public MessageChannel orderProcessChannel(){
        return new QueueChannel(10);
    }

    @Bean("order-queue-reply-channel")
    public MessageChannel orderReplyChannel(){
        return new QueueChannel(10);
    }

    @ServiceActivator(inputChannel = "request-queue-in-channel",
            outputChannel = "order-queue-process-channel")
    public Message<Order> placeOrder(Message<Order> order){
        return order;
    }
    @ServiceActivator(inputChannel = "order-queue-process-channel",
            outputChannel = "order-queue-reply-channel" ,
            poller = @Poller(fixedDelay = "100",maxMessagesPerPoll = "1"))
    public Message<Order> processOrder(Message<Order> order){
            order.getPayload().setOrderStatus("Order queue placed successfully");
    return order;
    }


    @ServiceActivator(inputChannel = "order-queue-reply-channel" , poller = @Poller(fixedDelay = "100",maxMessagesPerPoll = "1")
    )
    public void replyOrder(Message<Order> order){

        MessageChannel messageChannel= (MessageChannel) order.getHeaders().getReplyChannel();

        messageChannel.send(order);

    }




}
