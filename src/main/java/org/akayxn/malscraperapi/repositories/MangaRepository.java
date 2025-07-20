package org.akayxn.malscraperapi.repositories;

import org.akayxn.malscraperapi.models.Manga;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MangaRepository {
    private final JdbcTemplate jdbc;

    public MangaRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public List<Manga> findAllManga(){
        String sql= "SELECT * FROM manga";
        return jdbc.query(sql, new MangaRowMapper());
    }

    public Manga findMangaByRank(int rank){
        String sql= "SElECT * FROM manga where rank=?";
        return jdbc.queryForObject(sql, new MangaRowMapper(),rank);
    }

    public Manga findMangaById(int id){
        String sql= "SElECT * FROM manga where id=?";
        return jdbc.queryForObject(sql, new MangaRowMapper(),id);
    }

}
