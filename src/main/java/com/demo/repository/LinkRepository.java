package com.demo.repository;

import com.demo.model.Link;

public interface LinkRepository {
    Link findByFullUrl(String fullUrl);

    Link save(Link link);
}
