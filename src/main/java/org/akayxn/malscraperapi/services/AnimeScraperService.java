package org.akayxn.malscraperapi.services;

import org.akayxn.malscraperapi.models.Anime;
import org.akayxn.malscraperapi.models.Manga;
import org.akayxn.malscraperapi.repositories.AnimeRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public class AnimeScraperService implements ScraperService{

    AnimeRepository animeRepository;

     AnimeScraperService(AnimeRepository animeRepository){
        this.animeRepository = animeRepository;
    }
        @Value("${anime.source.url}")
        String url;



        @Override
        public void scrape() {
            try{
//            Connects to the website, and get the html document
                Document document = Jsoup.connect(url).get();

//            finding all the needed elements
                Elements mangaRows = document.select("tr.ranking-list");
                var idIterator = document.select("[id^=area]")
                        .stream()
                        .map(Element::id)
                        .filter(id -> id.length() > 4)
                        .map(id -> id.substring(4))
                        .filter(suffix -> suffix.matches("\\d+"))
                        .map(Integer::valueOf).iterator();  // now we're mapping to Integer, not int
                for(Element element : mangaRows){
                    Anime anime = new Anime();

//              MalId
                    int malId = idIterator.next();
                    System.out.println(malId);
                    anime.setMalId(malId);


//                Ranks
                    Element rankSpan = element.selectFirst("td.rank.ac span");
                    if(rankSpan!=null){
                        System.out.println(rankSpan);
                        anime.setRank(Integer.parseInt(rankSpan.text()));
                    }

//                Title
                    Element titleHeading = element.selectFirst("td.title.al div.detail h3.manga_h3 a");
                    if(titleHeading != null){
                        System.out.println(titleHeading);
                        anime.setTitle(titleHeading.text());
                    }

//              Score
                    Element scoreSpan = element.selectFirst("td.score.ac div.js-top-ranking-score-col span.text.on");
                    if(scoreSpan!=null){
                        System.out.println(scoreSpan);
                        anime.setScore(Double.parseDouble(scoreSpan.text()));
                    }

                    animeRepository.save(anime);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
}









