package com.vella.URLShortenerManagmenetService.Service;


import com.vella.URLShortenerManagmenetService.Model.Url;
import com.vella.URLShortenerManagmenetService.Repository.UrlRepo;
import com.vella.URLShortenerManagmenetService.exception.CustomErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlService {

    private final UrlRepo urlRepo;

    public Url createShortUrl(String realUrl) {
        if (realUrl == null || realUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("No url was set");
        }

        try {
            String baseShortUrl = "http://localhost:8080/";
            String uniqueUrl = realUrl;
            String hash;

            hash = DigestUtils.md5Hex(uniqueUrl);

            String shortUrl = baseShortUrl + hash;

            Optional<Url> existingUrl = urlRepo.findByShortUrl(shortUrl);
            if (existingUrl.isEmpty()) {
                Url url = new Url();
                url.setRealURL(realUrl);
                url.setShortURL(shortUrl);
                return urlRepo.save(url);
            }else{
                return existingUrl.get();
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed creating short URL", e);
        }
    }

    public String deleteRoute(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("No id was set");
        }
        try {

            Optional<Url> urlOp = urlRepo.findById(id);
            if (urlOp.isEmpty()) {
                throw new CustomErrorException("Cant find rout with specified id");
            }
            Url url = urlOp.get();
            urlRepo.delete(url);
            return ("Deleted rout with id " + String.valueOf(id));

        } catch (Exception e) {
            throw new CustomErrorException("Field deleting route", e);
        }
    }

    public Page<Url> viewRoutes(Integer pageNumber, Integer pageSize) {
        try {
            if (pageNumber == null) {
                throw new IOException("Page number wasn't set");
            }

            if (pageSize == null) {
                throw new IOException("Page size wasn't set");
            }

            Pageable pageable = PageRequest.of(pageNumber, pageSize);

            return urlRepo.findAll(pageable);

        } catch (Exception e) {
            throw new CustomErrorException("Field fetching routes");
        }
    }


}
