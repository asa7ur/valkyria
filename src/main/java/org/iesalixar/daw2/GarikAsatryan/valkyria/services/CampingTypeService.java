package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingTypeCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingTypeDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.CampingType;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.CampingTypeMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.CampingTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampingTypeService {
    private static final Logger logger = LoggerFactory.getLogger(CampingTypeService.class);
    private final CampingTypeRepository campingTypeRepository;
    private final CampingTypeMapper campingTypeMapper;

    public List<CampingTypeDTO> getAllCampingTypes() {
        try {
            return campingTypeMapper.toDTOList(campingTypeRepository.findAll());
        } catch (Exception e) {
            logger.error("Error al obtener tipos de camping: {}", e.getMessage());
            throw new RuntimeException("Error al listar tipos de camping");
        }
    }

    public CampingTypeDTO getCampingTypeById(Long id) {
        return campingTypeRepository.findById(id)
                .map(campingTypeMapper::toDTO)
                .orElseThrow(() -> new AppException("msg.camping.not-found", id));
    }

    @Transactional
    public CampingTypeDTO createCampingType(CampingTypeCreateDTO dto) {
        try {
            CampingType entity = campingTypeMapper.toEntity(dto);
            return campingTypeMapper.toDTO(campingTypeRepository.save(entity));
        } catch (Exception e) {
            logger.error("Error al crear tipo de camping: {}", e.getMessage());
            throw new RuntimeException("No se pudo crear el tipo de camping");
        }
    }

    @Transactional
    public CampingTypeDTO updateCampingType(Long id, CampingTypeCreateDTO dto) {
        CampingType existing = campingTypeRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.camping.not-found", id));

        campingTypeMapper.updateEntityFromDTO(dto, existing);
        return campingTypeMapper.toDTO(campingTypeRepository.save(existing));
    }

    @Transactional
    public void deleteCampingType(Long id) {
        if (!campingTypeRepository.existsById(id)) {
            throw new AppException("msg.camping.not-found", id);
        }
        campingTypeRepository.deleteById(id);
    }
}