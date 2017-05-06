package com.demo.repository;

import com.demo.model.Link;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends CrudRepository<Link, String> {
    Link findByFullUrl(String fullUrl);

    Link save(Link link);
}
