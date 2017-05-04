package com.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Link {

    @JsonProperty("short_url")
    private String shortUrl;

    @JsonProperty("full_url")
    private String fullUrl;

    public Link(String shortUrl, String fullUrl) {
        this.shortUrl = shortUrl;
        this.fullUrl = fullUrl;
    }
}
