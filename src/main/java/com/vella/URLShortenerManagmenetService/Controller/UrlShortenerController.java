package com.vella.URLShortenerManagmenetService.Controller;


import com.vella.URLShortenerManagmenetService.Model.Url;
import com.vella.URLShortenerManagmenetService.Service.UrlService;
import com.vella.URLShortenerManagmenetService.exception.CustomErrorException;
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
    public Url createShortUrl(@RequestBody String realUrl)throws CustomErrorException, IOException {
        return service.createShortUrl(realUrl);
    }

    @DeleteMapping("/delete")
    public String deleteRoute(@RequestBody Long id)throws CustomErrorException, IOException{
        return service.deleteRoute(id);
    }

    @GetMapping("/seeRoutes/{pageNumber}/{pageSize}")
    public Page<Url> viewRoutes(@PathVariable Integer pageNumber, @PathVariable Integer pageSize)throws CustomErrorException, IOException{
        return service.viewRoutes(pageNumber, pageSize);
    }
}
