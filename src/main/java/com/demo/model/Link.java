package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private int clickCount;

    protected Link() {
        // for hibernate
    }

    public Link(String shortUrl, String fullUrl) {
        this.shortUrl = shortUrl;
        this.fullUrl = fullUrl;
    }

    public Link(String shortUrl, String fullUrl, int clickCount) {
        this.shortUrl = shortUrl;
        this.fullUrl = fullUrl;
        this.clickCount = clickCount;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public int getClickCount() {
        return clickCount;
    }
}
