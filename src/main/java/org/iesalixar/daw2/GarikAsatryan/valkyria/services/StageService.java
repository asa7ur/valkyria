package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.StageCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.StageDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Stage;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.StageMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.StageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StageService {
    private static final Logger logger = LoggerFactory.getLogger(StageService.class);
    private final StageRepository stageRepository;
    private final StageMapper stageMapper;

    public Page<StageDTO> getAllStages(String searchTerm, Pageable pageable) {
        logger.info("Buscando escenarios...");
        Page<Stage> stagePage;
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            stagePage = stageRepository.searchStages(searchTerm, pageable);
        } else {
            stagePage = stageRepository.findAll(pageable);
        }
        return stagePage.map(stageMapper::toDTO);
    }

    public Optional<StageDTO> getStageById(Long id) {
        logger.info("Buscando detalle del escenario con ID: {}", id);
        return stageRepository.findById(id).map(stageMapper::toDTO);
    }

    @Transactional
    public StageDTO createStage(StageCreateDTO stageCreateDTO) {
        Stage stage = stageMapper.toEntity(stageCreateDTO);
        Stage saved = stageRepository.save(stage);
        logger.info("Escenario creado con éxito: {}", saved.getName());
        return stageMapper.toDTO(saved);
    }

    @Transactional
    public StageDTO updateStage(Long id, StageCreateDTO stageCreateDTO) {
        Stage existingStage = stageRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.stage.not-found", id));

        stageMapper.updateEntityFromDTO(stageCreateDTO, existingStage);
        logger.info("Escenario con ID {} actualizado correctamente", id);
        return stageMapper.toDTO(stageRepository.save(existingStage));
    }

    @Transactional
    public void deleteStage(Long id) {
        if (!stageRepository.existsById(id)) {
            throw new AppException("msg.stage.not-found", id);
        }
        stageRepository.deleteById(id);
        logger.info("Escenario con ID {} eliminado", id);
    }
}