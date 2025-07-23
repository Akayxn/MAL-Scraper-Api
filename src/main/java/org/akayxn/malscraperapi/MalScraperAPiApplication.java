package org.akayxn.malscraperapi;

import org.akayxn.malscraperapi.services.AnimeScraperService;
import org.akayxn.malscraperapi.services.MangaScraperService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MalScraperAPiApplication {

    public static void main(String[] args) {
        var context=SpringApplication.run(MalScraperAPiApplication.class, args);

        var scraperService = context.getBean(AnimeScraperService.class);
        scraperService.scrape();


    }

}
