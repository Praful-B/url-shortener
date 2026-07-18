package com.url.controller;


import com.url.model.dto.LongUrlDTO;
import com.url.model.dto.ShortenUrlDTO;
import com.url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class urlController {
    @Autowired
    UrlService urlService;

    @PostMapping("/shorten")
    public ShortenUrlDTO shortenUrl(@RequestBody LongUrlDTO longUrlDTO){
        return urlService.generateShortCode(longUrlDTO);
    }

    @GetMapping("/shorten/{code}")
    public ShortenUrlDTO findShortenUrl(@PathVariable String code){
        return  urlService.findShortenUrlByCode(code);
    }

    @PostMapping("/shorten/{code}")
    public ShortenUrlDTO updateShortenUrl(@PathVariable String shortUrlCode, @RequestBody LongUrlDTO longUrlDTO){
        return urlService.updateUrl(shortUrlCode, longUrlDTO.url());
    }

    @DeleteMapping("/shorten/{code}")
    public HttpStatus deleteShortenUrl(@PathVariable String code){
        return urlService.deleteUrl(code);
    }
}
