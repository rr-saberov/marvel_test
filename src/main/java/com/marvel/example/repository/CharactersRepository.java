package com.marvel.example.repository;

import com.marvel.example.entity.Characters;
import com.marvel.example.entity.Comics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CharactersRepository extends JpaRepository<Characters, Integer> {

    @Query("SELECT c " +
            "FROM Characters c ")
    Page<Characters> getAllCharacters(Pageable pageable);

    @Query(value = "SELECT * FROM characters " +
            "WHERE name LIKE %:query% " +
            "ORDER BY id", nativeQuery = true)
    Page<Characters> searchCharacters(@Param("query") String query, Pageable pageable);

    @Query("SELECT ch " +
            "FROM Characters ch " +
            "WHERE ch.id = :id")
    Characters getCharactersById(@Param("id") Integer id);

    //TODO :
    @Query("SELECT comics " +
            "FROM Comics comics " +
            "JOIN Characters ch " +
            "WHERE ch.id = :id " +
            "ORDER BY ch.name")
    Page<Comics> getComicsWithCharacters(@Param("id") Integer id, Pageable pageable);

    void deleteById(Integer id);

}
