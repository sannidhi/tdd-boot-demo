package com.demo.service;

import com.demo.model.Link;
import com.demo.repository.LinkRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlShortenerTest {

    private static final String LONG_URL = "https://veryLongUrl.com";
    private static final String SHORT_URL = "short.ly";
    private static final com.demo.model.Link LINK = new Link(SHORT_URL, LONG_URL);

    private UrlShortener urlShortener;

    @Mock
    private LinkRepository linkRepository;

    @Before
    public void setUp() throws Exception {
        urlShortener = new UrlShortener(linkRepository);
    }

    @Test
    public void shorten_lookUpDatabase_AndReturnsShortenedUrl() {
        when(linkRepository.findByFullUrl(anyString())).thenReturn(LINK);

        Link link = urlShortener.shorten(LONG_URL);

        assertThat(link.getShortUrl()).isEqualTo(SHORT_URL);

        verify(linkRepository).findByFullUrl(anyString());
    }
}
