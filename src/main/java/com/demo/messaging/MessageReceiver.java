package com.demo.messaging;

import com.demo.repository.LinkRepository;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private LinkRepository linkRepository;

    public MessageReceiver(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public void receiveMessage(String message) {
        linkRepository.incrementClickCountByOne(message);
    }
}
