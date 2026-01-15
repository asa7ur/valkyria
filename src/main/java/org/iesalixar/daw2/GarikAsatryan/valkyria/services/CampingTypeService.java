package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingTypeCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingTypeDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.CampingType;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.CampingTypeMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.CampingTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampingTypeService {
    private final CampingTypeRepository campingTypeRepository;
    private final CampingTypeMapper campingTypeMapper;

    public List<CampingTypeDTO> getAllCampingTypes() {
        return campingTypeMapper.toDTOList(campingTypeRepository.findAll());
    }

    public CampingTypeDTO getCampingTypeById(Long id) {
        return campingTypeRepository.findById(id)
                .map(campingTypeMapper::toDTO)
                .orElseThrow(() -> new AppException("msg.camping.not-found", id));
    }

    @Transactional
    public CampingTypeDTO createCampingType(CampingTypeCreateDTO dto) {
        CampingType entity = campingTypeMapper.toEntity(dto);
        return campingTypeMapper.toDTO(campingTypeRepository.save(entity));
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