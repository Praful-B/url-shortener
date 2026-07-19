package com.url.repository;

import com.url.model.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UrlRepository extends JpaRepository<Url, UUID> {
    Url findByShortenCode(String code);
}
