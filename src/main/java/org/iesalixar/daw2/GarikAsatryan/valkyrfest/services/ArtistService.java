package org.iesalixar.daw2.GarikAsatryan.valkyrfest.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.entities.Artist;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Optional<Artist> getArtistById(Long id) {
        return artistRepository.findById(id);
    }

    @Transactional
    public void saveArtist(Artist artist) {
        artistRepository.save(artist);
    }

    @Transactional
    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }
}
