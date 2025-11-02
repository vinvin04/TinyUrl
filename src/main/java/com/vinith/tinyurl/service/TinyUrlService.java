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
import java.util.Optional;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

@Service
public class TinyUrlService {
    @Autowired
    private TinyUrlRepository tinyUrlRepository;

    @Autowired
    private BloomService bloomService;

    private static final String STRING_KEY_PREFIX = "redi2read:strings:";

    public static String getCRCHexHash(String longUrl) {
        Checksum crc32 = new CRC32();
        crc32.update(longUrl.getBytes(), 0, longUrl.getBytes().length);
        long crcValue = crc32.getValue();
        String hash = Long.toHexString(crcValue);
        System.out.println("CRC32 (hex): " + hash);
        hash = hash.substring(0, 7);
        return hash;
    }

    public ResponseEntity<CreateResponse> createShortLink(CreateRequest createRequest) {
        String shortUrl = getCRCHexHash(createRequest.getLongUrl());
        String salt = "::salted::";
        String saltedLongUrl = createRequest.getLongUrl();
        while(bloomService.exists(shortUrl)) {
            saltedLongUrl = saltedLongUrl + salt;
            shortUrl = getCRCHexHash(saltedLongUrl);
        }
        bloomService.add(shortUrl);

        TinyUrl tinyUrl = new TinyUrl();
        tinyUrl.setLongUrl(createRequest.getLongUrl());
        tinyUrl.setShortUrl(shortUrl);
        tinyUrlRepository.save(tinyUrl);
        CreateResponse response = new CreateResponse().shortUrl(shortUrl);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Void> getShortLink(String shortUrl) {
        Optional<TinyUrl> tinyUrl = tinyUrlRepository.findByShortUrl(shortUrl);
        if (tinyUrl.isEmpty()) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        ResponseEntity<Void> response = new ResponseEntity<>(MultiValueMap.fromSingleValue(Map.of("Location",
                tinyUrl.get().getLongUrl()))
                , HttpStatusCode.valueOf(302));
        return response;
    }
}
