package com.demo.service;

import com.demo.model.Link;
import com.demo.repository.LinkRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestDatabase
public class UrlCachingTest {

    private static final String LONG_URL = "https://veryLongUrl.com";
    private static final String SHORT_URL = "short.ly";
    private static final Link LINK = new Link(SHORT_URL, LONG_URL);

    @Autowired
    private UrlShortener urlShortener;

    @MockBean
    private LinkRepository linkRepository;

    @Test
    public void expand_shouldFetchCachedFullUrl() {
        Link LINK2 = new Link("a", "abcd");
        when(linkRepository.findByShortUrl(SHORT_URL)).thenReturn(LINK, LINK2);
        Link firstFetch = urlShortener.expand(SHORT_URL);
        assertThat(firstFetch).isEqualTo(LINK);

        Link secondFetch = urlShortener.expand(SHORT_URL);
        verify(linkRepository, times(1)).findByShortUrl(SHORT_URL);
        assertThat(secondFetch).isEqualTo(LINK);

    }
}
