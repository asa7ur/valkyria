package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.PerformanceCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.PerformanceDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Artist;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Performance;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Stage;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.PerformanceMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.ArtistRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.PerformanceRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.StageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerformanceService {
    private static final Logger logger = LoggerFactory.getLogger(PerformanceService.class);

    private final PerformanceRepository performanceRepository;
    private final ArtistRepository artistRepository;
    private final StageRepository stageRepository;
    private final PerformanceMapper performanceMapper;

    /**
     * Obtiene actuaciones paginadas con búsqueda opcional por nombre de artista o escenario.
     */
    public Page<PerformanceDTO> getAllPerformances(String searchTerm, Pageable pageable) {
        logger.info("Buscando actuaciones...");
        Page<Performance> performancePage;
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            performancePage = performanceRepository.searchPerformances(searchTerm, pageable);
        } else {
            performancePage = performanceRepository.findAll(pageable);
        }
        return performancePage.map(performanceMapper::toDTO);
    }

    public List<PerformanceDTO> getAllPerformances() {
        return performanceRepository.findAll().stream()
                .map(performanceMapper::toDTO)
                .toList();
    }

    /**
     * Obtiene el detalle de una actuación por ID.
     */
    public Optional<PerformanceDTO> getPerformanceById(Long id) {
        logger.info("Buscando detalle de la actuación con ID: {}", id);
        return performanceRepository.findById(id).map(performanceMapper::toDTO);
    }

    /**
     * Crea una nueva actuación validando solapamientos.
     */
    @Transactional
    public PerformanceDTO createPerformance(PerformanceCreateDTO dto) {
        checkOverlap(dto, null);

        Artist artist = artistRepository.findById(dto.getArtistId())
                .orElseThrow(() -> new AppException("msg.artist.not-found", dto.getArtistId()));
        Stage stage = stageRepository.findById(dto.getStageId())
                .orElseThrow(() -> new AppException("msg.stage.not-found", dto.getStageId()));

        Performance performance = performanceMapper.toEntity(dto);
        performance.setArtist(artist);
        performance.setStage(stage);

        Performance saved = performanceRepository.save(performance);
        logger.info("Actuación creada: Artista {} en Escenario {}", artist.getName(), stage.getName());
        return performanceMapper.toDTO(saved);
    }

    /**
     * Actualiza una actuación existente.
     */
    @Transactional
    public PerformanceDTO updatePerformance(Long id, PerformanceCreateDTO dto) {
        Performance existingPerformance = performanceRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.performance.not-found", id));

        // 2. Validar solapamiento (Pasamos el ID de la URL para excluirlo de la búsqueda)
        checkOverlap(dto, id);

        Artist artist = artistRepository.findById(dto.getArtistId())
                .orElseThrow(() -> new AppException("msg.artist.not-found", dto.getArtistId()));
        Stage stage = stageRepository.findById(dto.getStageId())
                .orElseThrow(() -> new AppException("msg.stage.not-found", dto.getStageId()));

        performanceMapper.updateEntityFromDTO(dto, existingPerformance);
        existingPerformance.setArtist(artist);
        existingPerformance.setStage(stage);

        Performance updated = performanceRepository.save(existingPerformance);
        logger.info("Actuación con ID {} actualizada correctamente", id);
        return performanceMapper.toDTO(updated);
    }

    /**
     * Elimina una actuación.
     */
    @Transactional
    public void deletePerformance(Long id) {
        if (!performanceRepository.existsById(id)) {
            throw new AppException("msg.performance.not-found", id);
        }
        performanceRepository.deleteById(id);
        logger.info("Actuación con ID {} eliminada", id);
    }

    /**
     * Lógica centralizada de validación de horarios
     */
    private void checkOverlap(PerformanceCreateDTO dto, Long currentId) {
        boolean overlaps = performanceRepository.existsOverlappingPerformance(
                dto.getStageId(),
                dto.getStartTime(),
                dto.getEndTime(),
                currentId
        );

        if (overlaps) {
            // Lanzamos la excepción que tu GlobalExceptionHandler ya sabe traducir
            throw new AppException("msg.validation.performance.overlap");
        }
    }
}