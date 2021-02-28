package com.marvel.example.repository;

import com.marvel.example.model.Characters;
import com.marvel.example.model.Comics;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ComicsRepository {
    private final Logger logger = Logger.getLogger(ComicsRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ComicsRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Comics> retrieveAll() {
        List<Comics> comicsList = jdbcTemplate.query("SELECT * FROM comics", (ResultSet rs, int rowNum) -> {
            Comics comics = new Comics();
            comics.setId(rs.getInt("id"));
            comics.setTitle(rs.getString("title"));
            return comics;
        });
        return new ArrayList<>(comicsList);
    }

    public Comics searchComicsById(Integer comicsId) {
        return retrieveAll().stream().filter(comics ->
                comics.getId() == comicsId).findFirst().get();
    }

    public List<Characters> searchCharactersInComics(Integer comicsId) {
        return retrieveAll().stream().filter(comics ->
                comics.getId() == comicsId).findFirst().get().getCharacters();
    }

    public Comics addComics(Comics comics) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("title", comics.getTitle());
        jdbcTemplate.update("INSERT INTO comics(title) VALUES(:title)", parameterSource);
        logger.info("store new comics " + comics);
        return comics;
    }

    public boolean removeComicsById(Integer id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        jdbcTemplate.update("DELETE FROM comics WHERE id = :id", parameterSource);
        logger.info("remove comics completed");
        return true;
    }
}
