package com.vella.URLShortenerManagementService.controller;


import com.vella.URLShortenerManagementService.model.Url;
import com.vella.URLShortenerManagementService.service.UrlService;
import com.vella.URLShortenerManagementService.exception.CustomErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/shortUrl")
@RequiredArgsConstructor
@Slf4j
public class UrlShortenerController {

    private final UrlService service;

    @PostMapping("/create")
    public Url createShortUrl(@RequestBody String realUrl) throws CustomErrorException, IOException {
        return service.createShortUrl(realUrl);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRoute(@PathVariable Long id) throws CustomErrorException, IOException {
        return service.deleteRoute(id);
    }

    @GetMapping("/viewRoutes/{pageNumber}/{pageSize}")
    public Page<Url> viewRoutes(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) throws CustomErrorException, IOException {
        return service.viewRoutes(pageNumber, pageSize);
    }
}
