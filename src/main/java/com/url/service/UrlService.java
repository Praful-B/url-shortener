package com.url.service;

import com.url.error.UrlNotFoundException;
import com.url.model.dto.LongUrlDTO;
import com.url.model.dto.ShortenUrlDTO;
import com.url.model.entity.Url;
import com.url.repository.UrlRepository;
import com.url.util.WordSelection;
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
        return toDto(url);
    }

    public ShortenUrlDTO findShortenUrlByCode(String code){
        Url url =  urlRepository.findShortenUrlByCode(code);
        return toDto(url);
    }

    public ShortenUrlDTO updateUrl(String code , String newUrl){
        Url url =  urlRepository.findShortenUrlByCode(code);
        url.setOriginalUrl(newUrl);
        urlRepository.save(url);
        return toDto(url);
    }

    public void deleteUrl(String shortUrlCode){
        Url url = urlRepository.findShortenUrlByCode(shortUrlCode);
        if(url == null){
            throw new UrlNotFoundException(shortUrlCode);
        }
        urlRepository.delete(url);
    }

    private ShortenUrlDTO toDto(Url url){
        return new ShortenUrlDTO(
                url.getId(),
                url.getOriginalUrl(),
                url.getShortenCode(),
                url.getCreatedAt(),
                url.getUpdatedAt()
        );
    }
}
