package com.vella.URLShortenerManagmenetService.Service;


import com.vella.URLShortenerManagmenetService.Model.HashtebleUrl;
import com.vella.URLShortenerManagmenetService.Model.Url;
import com.vella.URLShortenerManagmenetService.Repository.HashTableRepo;
import com.vella.URLShortenerManagmenetService.Repository.UrlRepo;
import com.vella.URLShortenerManagmenetService.exception.CustomErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UrlService {

    private final UrlRepo urlRepo;
    private final HashTableRepo tableRepo;

    public Url createShortUrl(String realUrl) {
        try {

            if(realUrl.isEmpty()){
                throw new IOException("No url was set");
            }

            Optional<HashtebleUrl> tableOp = tableRepo.findById(1L);

            if (tableOp.isEmpty()) {
                throw new CustomErrorException("No hash table in database");
            }

            HashtebleUrl tableUrl = tableOp.get();
            Hashtable<Long, String> hashtable = tableUrl.getHashtable();
            Url url = new Url();
            url.setRealURL(realUrl);
            Url savedUrl = urlRepo.save(url);

            hashtable.put(url.getId(), url.getRealURL());
            String hash = String.valueOf(url.getId().hashCode());

            String shortUrl = "http://localhost:8080/" + "hash";

            savedUrl.setShortURL(shortUrl);


            tableUrl.setHashtable(hashtable);
            tableRepo.save(tableUrl);

            return urlRepo.save(savedUrl);

        } catch (Exception e) {
            throw new CustomErrorException("Failed creating short URL", e);
        }
    }

    public String deleteRoute(Long id) {
        try {

            Optional<Url> urlOp = urlRepo.findById(id);
            if (urlOp.isEmpty()) {
                throw new CustomErrorException("Cant find rout with specified id");
            }

            Optional<HashtebleUrl> tableOp = tableRepo.findById(1L);

            if (tableOp.isEmpty()) {
                throw new CustomErrorException("No hash table in database");
            }

            HashtebleUrl tableUrl = tableOp.get();
            Hashtable<Long, String> hashtable = tableUrl.getHashtable();



            Url url = urlOp.get();

            urlRepo.delete(url);

            hashtable.remove(id);
            tableUrl.setHashtable(hashtable);
            tableRepo.save(tableUrl);

            return ("Deleted rout with id " + String.valueOf(id));

        } catch (Exception e) {
            throw new CustomErrorException("Field deleting route", e);
        }
    }

    public Page<Url> viewRoutes(Integer pageNumber, Integer pageSize){
        try {
            if(pageNumber == null){
                throw new IOException("Page number wasn't set");
            }

            if(pageSize == null){
                throw new IOException("Page size wasn't set");
            }

            Pageable pageable = PageRequest.of(pageNumber, pageSize);

            return urlRepo.findAll(pageable);

        }catch (Exception e){
            throw new CustomErrorException("Field fetching routes");
        }
    }



}
