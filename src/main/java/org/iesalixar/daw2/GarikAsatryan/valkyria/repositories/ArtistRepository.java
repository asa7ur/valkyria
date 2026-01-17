package org.iesalixar.daw2.GarikAsatryan.valkyria.repositories;

import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    @Query("SELECT a FROM Artist a WHERE " +
            "LOWER(a.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(a.genre) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(a.country) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Artist> searchArtists(@Param("searchTerm") String searchTerm, Pageable pageable);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByEmail(String email);

    List<Artist> findByLogoIsNotNull();
}
