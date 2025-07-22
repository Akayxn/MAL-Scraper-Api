package org.akayxn.malscraperapi.services;

import org.akayxn.malscraperapi.models.Manga;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public class MangaScraperService implements ScraperService<Manga> {

    @Override
    public Set<Manga> scrape() {
        try{
//            Connects to the website, and get the html document
            Document document = Jsoup.connect(url).get();

//            finding all the needed elements
            Elements mangaRows = document.select("tr.ranking-list");

            for(Element element : mangaRows){
                Manga manga = new Manga();

//              Id
                Element idAnchor = element.selectFirst("td.title.al a");
                String id = idAnchor.id();
                if(id != null){
                    System.out.println(id);
                    manga.setTitle(id.text());
                }


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



            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Value("${manga.source.url}")
    String url;









}
