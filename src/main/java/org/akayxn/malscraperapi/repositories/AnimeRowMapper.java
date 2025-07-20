package org.akayxn.malscraperapi.repositories;

import org.akayxn.malscraperapi.models.Anime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimeRowMapper implements RowMapper<Anime> {

    @Override
    public Anime mapRow(ResultSet rs, int rowNum) throws SQLException {
        Anime anime = new Anime();
        anime.setId(rs.getInt("id"));
        anime.setRank(rs.getInt("rank"));
        anime.setTitle(rs.getString("title"));
        anime.setScore(rs.getDouble("score"));
        return anime;
    }
}
