package org.akayxn.malscraperapi.repositories;

import org.akayxn.malscraperapi.models.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Integer> {

    Anime findByTitle(String title);
    List<Anime> findByScoreGreaterThanEqual(double score);
    List<Anime> findAllByOrderByRankAsc();

    Anime findByMalId(int malId);
}
