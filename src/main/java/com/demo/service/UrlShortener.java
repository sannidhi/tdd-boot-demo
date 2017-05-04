package com.demo.service;

import com.demo.model.Link;
import com.demo.repository.LinkRepository;

public class UrlShortener {
    private LinkRepository linkRepository;

    public UrlShortener(LinkRepository linkRepository) {

        this.linkRepository = linkRepository;
    }

    public Link shorten(String shortUrl) {
        return null;
    }
}
