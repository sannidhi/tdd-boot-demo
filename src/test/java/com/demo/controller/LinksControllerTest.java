package com.demo.controller;

import com.demo.model.Link;
import com.demo.service.UrlShortener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.demo.messaging.MessageReceiver.QUEUE;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(LinksController.class)
public class LinksControllerTest {

    private static final String SHORT_URL = "abc123";
    private static final String FULL_URL = "http://www.averylongurl.com";
    private Link mockLink = new Link(SHORT_URL, FULL_URL);

    @Autowired
    private LinksController linksController;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UrlShortener urlShortener;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    public void shorten_createsShortUrl() throws Exception {
        //Arrange
        when(urlShortener.shorten(anyString())).thenReturn(mockLink);

        //Act
        ResultActions result = mvc.perform(get("/shorten").param("fullUrl", FULL_URL));

        //Assert
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("short_url").value(SHORT_URL))
                .andExpect(jsonPath("full_url").value(FULL_URL));
    }

    @Test
    public void expand_returnsLongUrl() throws Exception {
        when(urlShortener.expand(SHORT_URL)).thenReturn(mockLink);
        String jsonResponse = "{\"short_url\":\"abc123\",\"full_url\":\"http://www.averylongurl.com\"}";

        mvc.perform(get("/expand").param("shortUrl",SHORT_URL))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }

    @Test
    public void expand_pushesClickUpdateEvent() {
        linksController.expand(SHORT_URL);

        verify(rabbitTemplate).convertAndSend(QUEUE, SHORT_URL);
    }
}

