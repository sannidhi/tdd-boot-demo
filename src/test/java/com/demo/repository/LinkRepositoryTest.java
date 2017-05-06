package com.demo.repository;

import com.demo.model.Link;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LinkRepositoryTest {

    private static final String FULL_URL = "http://www.averylongurl.com";
    private static final String SHORT_URL = "abc.ly";

    @Autowired
    private LinkRepository linkRepository;

    @After
    public void tearDown() throws Exception {
        linkRepository.deleteAll();
    }

    @Test
    public void findByFullUrl_shouldFetchUrlMatch() {
        linkRepository.save(new Link(SHORT_URL, FULL_URL));

        Link foundLink = linkRepository.findByFullUrl(FULL_URL);

        assertThat(foundLink).isNotNull();
        assertThat(foundLink.getShortUrl()).isEqualTo(SHORT_URL);
    }
}
