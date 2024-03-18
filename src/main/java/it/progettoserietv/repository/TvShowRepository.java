package it.progettoserietv.repository;

import it.progettoserietv.entity.Tbl_tv_show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TvShowRepository extends JpaRepository<Tbl_tv_show, String> {
    @Query( value = "SELECT * FROM tbl_tv_show WHERE LOWER(name) = LOWER(?)", nativeQuery = true)
    Tbl_tv_show findByNameIgnoreCase(String name);

    @Query( value = "SELECT * FROM tbl_tv_show WHERE LOWER(language) = LOWER(?)", nativeQuery = true)
    List<Tbl_tv_show> findByLanguageIgnoreCase(String language);
}



