package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.SponsorCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.SponsorDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.SponsorDetailDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Artist;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Sponsor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Stage;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.SponsorMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.SponsorRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.StageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SponsorService {
    private static final Logger logger = LoggerFactory.getLogger(SponsorService.class);
    private static final String SPONSORS_FOLDER = "sponsors";

    private final SponsorRepository sponsorRepository;
    private final StageRepository stageRepository;
    private final SponsorMapper sponsorMapper;
    private final FileService fileService;

    public Page<SponsorDTO> getAllSponsors(String searchTerm, Pageable pageable) {
        logger.info("Buscando patrocinadores...");

        Page<Sponsor> sponsorPage = (searchTerm != null && !searchTerm.trim().isEmpty())
                ? sponsorRepository.searchSponsors(searchTerm, pageable)
                : sponsorRepository.findAll(pageable);
        return sponsorPage.map(sponsorMapper::toDTO);
    }

    public Optional<SponsorDetailDTO> getSponsorById(Long id) {
        logger.info("Buscando detalle del patrocinador con ID: {}", id);
        return sponsorRepository.findById(id).map(sponsorMapper::toDetailDTO);
    }

    @Transactional
    public SponsorDTO createSponsor(SponsorCreateDTO dto) {
        Sponsor sponsor = sponsorMapper.toEntity(dto);

        updateSponsorStages(sponsor, dto.getStageIds());

        Sponsor savedSponsor = sponsorRepository.save(sponsor);
        logger.info("Patrocinador creado con éxito: {}", savedSponsor.getName());
        return sponsorMapper.toDTO(savedSponsor);
    }

    @Transactional
    public SponsorDTO updateSponsor(Long id, SponsorCreateDTO dto) {
        Sponsor existingSponsor = sponsorRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.sponsor.not-found", id));

        sponsorMapper.updateEntityFromDTO(dto, existingSponsor);
        updateSponsorStages(existingSponsor, dto.getStageIds());

        Sponsor updatedSponsor = sponsorRepository.save(existingSponsor);

        logger.info("Patrocinador con ID {} actualizado correctamente", id);
        return sponsorMapper.toDTO(updatedSponsor);
    }

    private void updateSponsorStages(Sponsor sponsor, List<Long> stageIds) {
        if (stageIds != null) {
            List<Stage> stages = stageRepository.findAllById(stageIds);
            sponsor.setStages(stages);
        }
    }

    @Transactional
    public String processAndSaveImage(Long id, MultipartFile file) {
        Sponsor sponsor = sponsorRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.sponsor.not-found", id));
        if (sponsor.getImage() != null) {
            fileService.deleteFile(sponsor.getImage(), SPONSORS_FOLDER);
        }
        String fileName = fileService.saveFile(file, SPONSORS_FOLDER);
        sponsor.setImage(fileName);
        sponsorRepository.save(sponsor);
        logger.info("Nuevo logo guardado para el patrocinador con ID: {}", id);
        return fileName;
    }

    /**
     * Elimina el logo de un patrocinador tanto del sistema de archivos como de la base de datos.
     */
    @org.springframework.transaction.annotation.Transactional
    public void deleteLogo(Long id) {
        Sponsor sponsor = sponsorRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.artist.not-found", id));

        if (sponsor.getImage() != null) {
            fileService.deleteFile(sponsor.getImage(), SPONSORS_FOLDER);
            sponsor.setImage(null);
            sponsorRepository.save(sponsor);
            logger.info("Logo del patrocinador {} eliminado correctamente", id);
        }
    }

    @Transactional
    public void deleteSponsor(Long id) {
        Sponsor sponsor = sponsorRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.sponsor.not-found", id));
        if (sponsor.getImage() != null) {
            fileService.deleteFile(sponsor.getImage(), SPONSORS_FOLDER);
        }
        sponsorRepository.delete(sponsor);
    }
}