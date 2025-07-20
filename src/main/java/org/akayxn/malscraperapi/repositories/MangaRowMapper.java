package org.akayxn.malscraperapi.repositories;

import org.akayxn.malscraperapi.models.Manga;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MangaRowMapper implements RowMapper<Manga> {


    @Override
    public Manga mapRow(ResultSet rs, int rowNum) throws SQLException {
        Manga manga = new Manga();
        manga.setId(rs.getInt("id"));
        manga.setRank(rs.getInt("rank"));
        manga.setTitle(rs.getString("title"));
        manga.setScore(rs.getDouble("score"));
        return manga;
    }
}
