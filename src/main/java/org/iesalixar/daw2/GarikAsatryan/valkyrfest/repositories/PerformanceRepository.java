package org.iesalixar.daw2.GarikAsatryan.valkyrfest.repositories;

import org.iesalixar.daw2.GarikAsatryan.valkyrfest.entities.Performance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    @Query("SELECT p FROM Performance p WHERE " +
            "LOWER(p.artist.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.stage.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Performance> searchPerformances(@Param("searchTerm") String searchTerm, Pageable pageable);

    /**
     * Comprueba si hay solapamiento de horarios en un escenario específico.
     * La lógica (A < Fin_B) AND (B_Inicio < Fin_A) detecta cualquier cruce de intervalos.
     */
    @Query("SELECT COUNT(p) > 0 FROM Performance p WHERE p.stage.id = :stageId " +
            "AND p.startTime < :endTime AND p.endTime > :startTime " +
            "AND (:id IS NULL OR p.id <> :id)")
    boolean existsOverlappingPerformance(@Param("stageId") Long stageId,
                                         @Param("startTime") LocalDateTime startTime,
                                         @Param("endTime") LocalDateTime endTime,
                                         @Param("id") Long id);
}
