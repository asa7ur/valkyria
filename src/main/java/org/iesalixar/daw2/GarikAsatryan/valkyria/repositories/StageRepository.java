package org.iesalixar.daw2.GarikAsatryan.valkyria.repositories;

import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Stage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StageRepository extends JpaRepository<Stage, Long> {
    @Query("SELECT s FROM Stage s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Stage> searchStages(@Param("searchTerm") String searchTerm, Pageable pageable);
}
