package com.vinith.tinyurl.service;

import com.vinith.tinyurl.model.TinyUrl;
import com.vinith.tinyurl.repository.TinyUrlRepository;
import org.openapitools.model.CreateRequest;
import org.openapitools.model.CreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Service
public class TinyUrlService {
    @Autowired
    private TinyUrlRepository tinyUrlRepository;

    public ResponseEntity<CreateResponse> createShortLink(CreateRequest createRequest) {
        TinyUrl tinyUrl = new TinyUrl();
        tinyUrl.setLongUrl(createRequest.getLongUrl());
        tinyUrl = tinyUrlRepository.save(tinyUrl);
        String shortUrl = Long.toString(tinyUrl.getShortUrl());
        CreateResponse response = new CreateResponse().shortUrl(shortUrl);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Void> getShortLink(String shortUrl) {
        String longUrl = tinyUrlRepository.getReferenceById(Long.parseLong(shortUrl)).getLongUrl();
        ResponseEntity<Void> response = new ResponseEntity<>(MultiValueMap.fromSingleValue(Map.of("Location", longUrl))
                , HttpStatusCode.valueOf(302));
        return response;
    }
}
