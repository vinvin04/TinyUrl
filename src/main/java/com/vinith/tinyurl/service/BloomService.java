package com.vinith.tinyurl.service;

import io.rebloom.client.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BloomService {
    private final Client client;
    private static final String FILTER_NAME = "tinyurl_filter";

    public BloomService(Client client) {
        this.client = client;
        try {
            // Create a Bloom Filter if it doesn’t exist (capacity=1000, error rate=0.01)
            client.createFilter(FILTER_NAME, 1000, 0.01);
        } catch (Exception e) {
            log.error("error while creating new bloom filter",e);
        }

    }

    public void add(String value) {
        client.add(FILTER_NAME, value);
    }

    public boolean exists(String value) {
        return client.exists(FILTER_NAME, value);
    }
}
