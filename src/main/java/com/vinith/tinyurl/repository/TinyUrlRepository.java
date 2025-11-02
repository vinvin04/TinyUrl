package com.vinith.tinyurl.repository;

import com.vinith.tinyurl.model.TinyUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TinyUrlRepository extends JpaRepository<TinyUrl, Long> {
    Optional<TinyUrl> findByShortUrl(String shortUrl);
}
