package com.demo.messaging;

import com.demo.repository.LinkRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MessageReceiverTest {

    private static final String SHORT_URL = "short.ly";
    private MessageReceiver messageReceiver;

    @Mock
    private LinkRepository repository;

    @Before
    public void setUp() throws Exception {
        messageReceiver = new MessageReceiver(repository);
    }

    @Test
    public void receive_shouldSendUrltoRepository() {
        messageReceiver.receiveMessage(SHORT_URL);

        verify(repository).incrementClickCountByOne(SHORT_URL);
    }

    @Test
    public void testRabbitListenerAnnotationExists() throws Exception {
        Method receiveMessage = MessageReceiver.class.getMethod("receiveMessage", String.class);
        RabbitListener annotation = receiveMessage.getAnnotation(RabbitListener.class);

        assertThat(annotation).isNotNull();
        QueueBinding[] queueBindings = annotation.bindings().clone();
        assertThat(queueBindings[0].value().value()).isEqualTo(MessageReceiver.QUEUE);
        assertThat(queueBindings[0].exchange().value()).isEqualTo(MessageReceiver.EXCHANGE);
        assertThat(queueBindings[0].exchange().type()).isEqualTo(ExchangeTypes.TOPIC);
    }
}
