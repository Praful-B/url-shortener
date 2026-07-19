package com.url.controller;


import com.url.model.dto.LongUrlDTO;
import com.url.model.dto.ShortenUrlDTO;
import com.url.model.dto.UrlDTO;
import com.url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {
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
    public ResponseEntity<String> deleteShortenUrl(@PathVariable String code){
        urlService.deleteUrl(code);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/shorten/{code}/stats")
    public UrlDTO getUrlStats(@PathVariable String code){
        return urlService.stats(code);
    }
}
