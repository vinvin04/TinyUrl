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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_generator")
    @SequenceGenerator(name = "id_seq_generator", sequenceName = "ID_SEQ", initialValue = 1000000,
            allocationSize = 1)
    private Long id;
    private String shortUrl;
    private String longUrl;
}
