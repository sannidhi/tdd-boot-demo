package com.demo.controller;

import com.demo.model.Link;
import com.demo.service.UrlShortener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinksController {

    private UrlShortener urlShortener;

    public LinksController(UrlShortener urlShortener) {
        this.urlShortener = urlShortener;
    }

    @GetMapping("shorten")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Link shorten (@RequestParam("fullUrl") String fullUrl) {
        return urlShortener.shorten(fullUrl);
    }

    @GetMapping("expand")
    @ResponseStatus(value = HttpStatus.OK)
    public Link expand (@RequestParam("shortUrl") String shortUrl) {
        return urlShortener.expand(shortUrl);
    }
}
