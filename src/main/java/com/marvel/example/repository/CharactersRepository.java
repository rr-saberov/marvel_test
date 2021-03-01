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
public class CharactersRepository {

    private final Logger logger = Logger.getLogger(CharactersRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CharactersRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Characters> retrieveAll() {
        List<Characters> characters = jdbcTemplate.query("SELECT * FROM characters", (ResultSet rs, int rowNum) -> {
            Characters character = new Characters();
            character.setId(rs.getInt("id"));
            character.setName(rs.getString("name"));
            character.setDescription(rs.getString("description"));
            return character;
        });
        return new ArrayList<>(characters);
    }

    public Characters searchCharacterById(Integer characterId) {
        return retrieveAll().stream().filter(characters ->
                characters.getId() == characterId).findFirst().get();
    }

    public List<Comics> searchComicsWithCharacter(Integer characterId) {
        return retrieveAll().stream().filter(ch ->
                ch.getId() == characterId).findFirst().get().getComics();
    }

    public Characters saveCharacter(Characters characters) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", characters.getName());
        parameterSource.addValue("description", characters.getDescription());
        jdbcTemplate.update("INSERT INTO characters(name, description)" +
                " VALUES(:name, :descritption)", parameterSource);
        logger.info("store new character " + characters);
        return characters;
    }

    public boolean removeCharacterById(Integer id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        jdbcTemplate.update("DELETE FROM characters WHERE id = :id", parameterSource);
        logger.info("remove character completed");
        return true;
    }
}
