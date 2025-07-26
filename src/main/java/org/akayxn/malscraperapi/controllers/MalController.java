package org.akayxn.malscraperapi.controllers;

import org.akayxn.malscraperapi.models.Anime;
import org.akayxn.malscraperapi.models.Manga;
import org.akayxn.malscraperapi.repositories.AnimeRepository;
import org.akayxn.malscraperapi.repositories.MangaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MalController {
    private MangaRepository mangaRepository;
    private AnimeRepository animeRepository;

    public MalController(MangaRepository mangaRepository, AnimeRepository animeRepository){
        this.mangaRepository = mangaRepository;
        this.animeRepository = animeRepository;
    }

    @GetMapping("/topAnime")
    public List<Anime> listAnime(){
        return animeRepository.findAll();
    }


    @GetMapping("/topManga")
    public List<Manga> listManga(){
        return mangaRepository.findAll();
    }




}
