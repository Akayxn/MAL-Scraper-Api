package org.akayxn.malscraperapi.repositories;

import org.akayxn.malscraperapi.models.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Integer> {

    Manga findByTitle(String title);
    List<Manga> findByScoreGreaterThanEqual(double score);
    List<Manga> findAllByOrderByRankAsc();
}
