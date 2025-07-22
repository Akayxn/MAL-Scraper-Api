package org.akayxn.malscraperapi.services;

import org.akayxn.malscraperapi.models.Anime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AnimeScraperService implements ScraperService<Anime>{

    @Override
    public Set<Anime> scrape() {
        return Set.of();
    }

    @Value("${anime.source.url}")
    String url;







}
