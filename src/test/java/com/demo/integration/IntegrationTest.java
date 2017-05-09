package com.demo.integration;

import com.demo.controller.LinksController;
import com.demo.model.Link;
import com.demo.repository.LinkRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private LinksController linksController;

    @Autowired
    private LinkRepository linkRepository;
    private static final String FULL_URL = "http://verylongUrl.com";

    @After
    public void tearDown() throws Exception {
        linkRepository.deleteAll();
    }

    @Test
    public void shortensUrl_andUpdatesClickCountOnExpand() throws InterruptedException {
        Link shortenedLink = linksController.shorten(FULL_URL);

        assertThat(shortenedLink).isNotNull();
        String shortenedUrl = linkRepository.findByFullUrl(FULL_URL).getShortUrl();
        assertThat(shortenedUrl.length()).isLessThan(FULL_URL.length());
    }
}
