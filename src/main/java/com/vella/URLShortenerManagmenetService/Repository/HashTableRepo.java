package com.vella.URLShortenerManagmenetService.Repository;

import com.vella.URLShortenerManagmenetService.Model.HashtebleUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashTableRepo extends JpaRepository<HashtebleUrl, Long> {

    @Override
    Optional<HashtebleUrl> findById(Long id);
}
