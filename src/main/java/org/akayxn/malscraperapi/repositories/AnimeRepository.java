package org.akayxn.malscraperapi.repositories;

import org.akayxn.malscraperapi.models.Anime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimeRepository {
    private final JdbcTemplate jdbc;

    public AnimeRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public List<Anime> listAllAnime(){
        String sql = "SELECT * FROM anime";
        return jdbc.query(sql,new AnimeRowMapper());
    }


    public Anime findAnimeByScore(int score){
        String sql = " SELECT * FROM anime WHERE score = ?";
        return jdbc.queryForObject(sql, new AnimeRowMapper(),score);
    }

    public Anime findAnimeById(int id){
        String sql = " SELECT * FROM anime WHERE id = ?";
        return jdbc.queryForObject(sql, new AnimeRowMapper(),id);
    }


}
