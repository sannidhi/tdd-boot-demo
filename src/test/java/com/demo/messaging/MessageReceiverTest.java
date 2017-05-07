package com.demo.messaging;

import com.demo.repository.LinkRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
}
