package com.vella.URLShortenerManagementService.repository;


import com.vella.URLShortenerManagementService.model.Url;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepo extends JpaRepository<Url, Long> {

    @Override
    Optional<Url> findById(Long id);

    Page<Url> findAll(Pageable pageable);

    Optional<Url> findByShortURL(String shortUrl);
}
