package com.demo.service;

import com.demo.model.Link;
import com.demo.repository.LinkRepository;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class UrlShortener {
    private LinkRepository linkRepository;

    public UrlShortener(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link shorten(String fullUrl) {
        Link link = linkRepository.findByFullUrl(fullUrl);
        if(link == null) {
            String shortUrl = Hashing.murmur3_32().hashString(fullUrl, StandardCharsets.UTF_8).toString().concat(".ly");
            link = new Link(shortUrl, fullUrl);
            linkRepository.save(link);
        }
        return link;
    }
}
