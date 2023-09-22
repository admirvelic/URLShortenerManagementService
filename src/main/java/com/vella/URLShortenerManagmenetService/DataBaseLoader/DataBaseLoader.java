package com.vella.URLShortenerManagmenetService.DataBaseLoader;

import com.vella.URLShortenerManagmenetService.Model.HashtebleUrl;
import com.vella.URLShortenerManagmenetService.Repository.HashTableRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final HashTableRepo repository;

    public DatabaseLoader(HashTableRepo repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        HashtebleUrl table = new HashtebleUrl();

        this.repository.save(table);
    }
}
