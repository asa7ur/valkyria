package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.LineupArtistDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.PerformanceDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.StageDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Performance;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.PerformanceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    public List<Performance> getAllPerformances() {
        return performanceRepository.findAll();
    }

    public Optional<Performance> getPerformanceById(Long id) {
        return performanceRepository.findById(id);
    }

    @Transactional
    public void savePerformance(Performance performance) {
        if (performance.getStage() != null && performance.getStartTime() != null && performance.getEndTime() != null) {
            boolean overlaps = performanceRepository.existsOverlappingPerformance(
                    performance.getStage().getId(),
                    performance.getStartTime(),
                    performance.getEndTime(),
                    performance.getId()
            );

            if (overlaps) {
                throw new AppException("msg.validation.performance.overlap", HttpStatus.BAD_REQUEST);
            }
        }
        performanceRepository.save(performance);
    }

    @Transactional
    public void deletePerformance(Long id) {
        performanceRepository.deleteById(id);
    }

    public Page<Performance> getAllPerformances(String searchTerm, Pageable pageable) {
        if (searchTerm != null && !searchTerm.isEmpty()) {
            return performanceRepository.searchPerformances(searchTerm, pageable);
        }
        return performanceRepository.findAll(pageable);
    }

    public List<PerformanceDTO> getAllPerformancesDTO() {
        return performanceRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PerformanceDTO convertToDTO(Performance p) {
        LineupArtistDTO artistDTO = new LineupArtistDTO(
                p.getArtist().getId(),
                p.getArtist().getName(),
                p.getArtist().getLogo(),
                p.getArtist().getGenre()
        );
        StageDTO stageDTO = new StageDTO(
                p.getStage().getId(),
                p.getStage().getName()
        );
        return new PerformanceDTO(
                p.getId(),
                p.getStartTime(),
                p.getEndTime(),
                artistDTO,
                stageDTO
        );
    }
}