package com.vella.URLShortenerManagmenetService.Repository;


import com.vella.URLShortenerManagmenetService.Model.Url;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UrlRepo extends JpaRepository<Url, Long> {

    @Override
    Optional<Url> findById(Long id);


    List<Url> findAll(Pageable pageable);
}
