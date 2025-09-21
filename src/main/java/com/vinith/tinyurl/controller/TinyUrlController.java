package com.vinith.tinyurl.controller;

import com.vinith.tinyurl.service.TinyUrlService;
import org.openapitools.api.ShortlinkApi;
import org.openapitools.model.CreateRequest;
import org.openapitools.model.CreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TinyUrlController implements ShortlinkApi {

    @Autowired
    TinyUrlService tinyUrlService;

    @Override
    public ResponseEntity<CreateResponse> createShortLink(CreateRequest createRequest) {
        return tinyUrlService.createShortLink(createRequest);
    }

    @Override
    public ResponseEntity<Void> getShortLink(String shortUrl) {
        return tinyUrlService.getShortLink(shortUrl);
    }
}
