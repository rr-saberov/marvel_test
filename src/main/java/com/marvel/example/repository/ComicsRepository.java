package com.marvel.example.repository;

import com.marvel.example.entity.Comics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ComicsRepository extends JpaRepository<Comics, Integer> {

    @Query("SELECT c " +
            "FROM Comics c")
    Page<Comics> getAllComics(Pageable pageable);

    @Query(value = "SELECT * FROM comics " +
            "WHERE title LIKE %:query% " +
            "ORDER BY id", nativeQuery = true)
    Page<Comics> searchComics(@Param("query") String query, Pageable pageable);

    @Query("SELECT comics " +
            "FROM Comics comics " +
            "WHERE comics.id = :id ")
    Comics getComicsById(@Param("id") Integer id);

    void deleteById(Integer id);

}




