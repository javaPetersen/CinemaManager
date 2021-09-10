package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.petersen.cinemamanager.entity.Seance;
import pl.petersen.cinemamanager.repository.SeanceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SeanceService {

    private final SeanceRepository seanceRepository;

    @Autowired
    public SeanceService(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }


    public Optional<Seance> findById(Long seanceId) {
        return seanceRepository.findById(seanceId);
    }

    public List<Seance> findAllActiveSeances() {
        return seanceRepository.findAllActiveSeancesOrderByDateAsc();
    }

    public void save(Seance seance) {
        seanceRepository.save(seance);
    }

    public boolean isHallAvailable(Seance seance) {
        List<Seance> seances = seanceRepository.findAll();
        seances.removeIf(s -> s.getId().equals(seance.getId()));
        return seances.stream()
                .filter(s -> s.getDate().equals(seance.getDate()) && s.getHall().equals(seance.getHall()))
                .allMatch(s -> seance.getTime().isAfter(s.getTime().plusMinutes(s.getMovie().getLength()))
                || seance.getTime().plusMinutes(seance.getMovie().getLength()).isBefore(s.getTime()));
    }
}
