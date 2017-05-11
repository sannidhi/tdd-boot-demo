package com.demo.messaging;

import com.demo.repository.LinkRepository;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {
    private LinkRepository linkRepository;

    public static final String EXCHANGE = "link-exchange";
    public static final String QUEUE = "link-queue";

    public MessageReceiver(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = QUEUE, durable = "false"),
            exchange = @Exchange(value = EXCHANGE, type = ExchangeTypes.TOPIC)))
    public void receiveMessage(String message) {
        linkRepository.incrementClickCountByOne(message);
    }
}
