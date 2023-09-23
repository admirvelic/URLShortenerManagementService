package com.vella.URLShortenerManagmenetService.Repository;


import com.vella.URLShortenerManagmenetService.Model.Url;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UrlRepo extends JpaRepository<Url, Long> {

    @Override
    Optional<Url> findById(Long id);

    Page<Url> findAll(Pageable pageable);

    Optional<Url> findByShortUrl(String shortUrl);
}
