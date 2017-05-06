package com.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Link {

    @JsonProperty("short_url")
    @Id
    private String shortUrl;

    @JsonProperty("full_url")
    private String fullUrl;

    public Link() {
        // for hibernate
    }

    public Link(String shortUrl, String fullUrl) {
        this.shortUrl = shortUrl;
        this.fullUrl = fullUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }
}
