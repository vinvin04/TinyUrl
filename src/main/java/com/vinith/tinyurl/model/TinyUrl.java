package com.vinith.tinyurl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
public class TinyUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shortUrl_seq_generator")
    @SequenceGenerator(name = "shortUrl_seq_generator", sequenceName = "SHORT_URL_SEQ", initialValue = 1000000,
            allocationSize = 1)
    private long shortUrl;
    private String longUrl;
}
