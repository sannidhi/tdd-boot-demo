package com.demo.integration;

import com.demo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MessageBrokerConnection {

    @Autowired
    private TopicExchange topicExchange;

    @Autowired
    private Queue queue;

    @Autowired
    private Binding binding;

    @Autowired
    private SimpleMessageListenerContainer messageListenerContainer;

    @Test
    public void testExchangeAndQueueSetup() throws Exception {
        assertThat(topicExchange.getName()).isEqualTo(Application.EXCHANGE);
        assertThat(this.queue.getName()).isEqualTo(Application.QUEUE);
    }

    @Test
    public void testBinding() {
        assertThat(binding.getExchange()).isEqualTo(Application.EXCHANGE);
        assertThat(binding.getDestination()).isEqualTo(Application.QUEUE);
    }

    @Test
    public void testMessageListener() {
        assertThat(messageListenerContainer.getQueueNames()).contains(Application.QUEUE);
    }
}
