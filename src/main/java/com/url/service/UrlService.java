package com.url.service;

import com.url.model.dto.LongUrlDTO;
import com.url.model.dto.ShortenUrlDTO;
import com.url.model.entity.Url;
import com.url.repository.UrlRepository;
import com.url.util.WordSelection;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class UrlService {

    UrlRepository urlRepository;
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public ShortenUrlDTO generateShortCode(LongUrlDTO longUrlDTO){

        WordSelection word = new WordSelection();

        String code = word.generateWordCombination(3);
        Url url = new Url();
        url.setOriginalUrl(longUrlDTO.url());
        url.setShortenCode(code);

        urlRepository.save(url);
        return new ShortenUrlDTO(
                url.getId(),
                url.getOriginalUrl(),
                url.getShortenCode(),
                url.getCreatedAt(),
                url.getUpdatedAt()
        );
    }

    public ShortenUrlDTO findShortenUrlByCode(String code){
        Url url =  urlRepository.findShortenUrlByCode(code);

        return new ShortenUrlDTO(
                url.getId(),
                url.getOriginalUrl(),
                url.getShortenCode(),
                url.getCreatedAt(),
                url.getUpdatedAt()
        );
    }

    public ShortenUrlDTO updateUrl(String shortUrlCode , String newUrl){
        Url url =  urlRepository.findShortenUrlByCode(shortUrlCode);
        url.setOriginalUrl(newUrl);

        return new ShortenUrlDTO(
                url.getId(),
                url.getOriginalUrl(),
                url.getShortenCode(),
                url.getCreatedAt(),
                url.getUpdatedAt()
        );
    }

    public HttpStatus deleteUrl(String shortUrlCode){
        Url url = urlRepository.findShortenUrlByCode(shortUrlCode);
        if(url == null){
            return HttpStatus.NOT_FOUND; // 404
        }

        urlRepository.delete(url);
        return HttpStatus.NO_CONTENT; // 204

    }

}
