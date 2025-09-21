package com.vinith.tinyurl.repository;

import com.vinith.tinyurl.model.TinyUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TinyUrlRepository extends JpaRepository<TinyUrl, Long> {
}
