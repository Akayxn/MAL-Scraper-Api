package org.akayxn.malscraperapi.services;

import org.akayxn.malscraperapi.models.Manga;
import org.akayxn.malscraperapi.repositories.MangaRepository;
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
public class MangaScraperService implements ScraperService {
    @Autowired
    public MangaRepository mangaRepository;


    @Value("${manga.source.url}")
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
                Manga manga = new Manga();

//              MalId
                int malId = idIterator.next();
                System.out.println(malId);
                manga.setMalId(malId);


//                Ranks
                Element rankSpan = element.selectFirst("td.rank.ac span");
                if(rankSpan!=null){
                    System.out.println(rankSpan);
                    manga.setRank(Integer.parseInt(rankSpan.text()));
                }

//                Title
                Element titleHeading = element.selectFirst("td.title.al div.detail h3.manga_h3 a");
                if(titleHeading != null){
                    System.out.println(titleHeading);
                    manga.setTitle(titleHeading.text());
                }

//              Score
                Element scoreSpan = element.selectFirst("td.score.ac div.js-top-ranking-score-col span.text.on");
                if(scoreSpan!=null){
                    System.out.println(scoreSpan);
                    manga.setScore(Double.parseDouble(scoreSpan.text()));
                }

                mangaRepository.save(manga);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }









}
