package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Camping;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.CampingType;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.CampingMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.CampingRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.CampingTypeRepository;
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
public class CampingService {
    private static final Logger logger = LoggerFactory.getLogger(CampingService.class);

    private final CampingRepository campingRepository;
    private final CampingTypeRepository campingTypeRepository; // Necesario para buscar el tipo
    private final CampingMapper campingMapper;

    public Page<CampingDTO> getAllCampings(String searchTerm, Pageable pageable) {
        Page<Camping> campingPage = (searchTerm != null && !searchTerm.trim().isEmpty())
                ? campingRepository.searchCampings(searchTerm, pageable)
                : campingRepository.findAll(pageable);
        return campingPage.map(campingMapper::toDTO);
    }

    public Optional<CampingDTO> getCampingById(Long id) {
        return campingRepository.findById(id).map(campingMapper::toDTO);
    }

    @Transactional
    public CampingDTO createCamping(CampingCreateDTO dto) {
        CampingType type = campingTypeRepository.findById(dto.getCampingTypeId())
                .orElseThrow(() -> new AppException("msg.camping.type-not-found", dto.getCampingTypeId()));

        Camping camping = campingMapper.toEntity(dto);
        camping.setCampingType(type);
        // Aquí podrías generar el QR si fuera necesario: camping.setQrCode(UUID.randomUUID().toString());

        return campingMapper.toDTO(campingRepository.save(camping));
    }

    @Transactional
    public CampingDTO updateCamping(Long id, CampingCreateDTO dto) {
        Camping existing = campingRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.camping.not-found", id));

        CampingType type = campingTypeRepository.findById(dto.getCampingTypeId())
                .orElseThrow(() -> new AppException("msg.camping.type-not-found", dto.getCampingTypeId()));

        campingMapper.updateEntityFromDTO(dto, existing);
        existing.setCampingType(type);

        return campingMapper.toDTO(campingRepository.save(existing));
    }

    @Transactional
    public void deleteCamping(Long id) {
        if (!campingRepository.existsById(id)) {
            throw new AppException("msg.camping.not-found", id);
        }
        campingRepository.deleteById(id);
    }
}