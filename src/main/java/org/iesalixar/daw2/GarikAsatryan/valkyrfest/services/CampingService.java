package org.iesalixar.daw2.GarikAsatryan.valkyrfest.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.entities.Camping;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.repositories.CampingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampingService {

    private final CampingRepository campingRepository;

    public List<Camping> getAllCampings() {
        return campingRepository.findAll();
    }

    public Optional<Camping> getCampingById(Long id) {
        return campingRepository.findById(id);
    }

    @Transactional
    public Camping saveCamping(Camping camping) {
        return campingRepository.save(camping);
    }

    @Transactional
    public void deleteCamping(Long id) {
        campingRepository.deleteById(id);
    }
}