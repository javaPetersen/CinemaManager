package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.petersen.cinemamanager.entity.Seance;
import pl.petersen.cinemamanager.repository.SeanceRepository;
import pl.petersen.cinemamanager.repository.TicketTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SeanceService {

    private final SeanceRepository seanceRepository;
    private final TicketTypeRepository ticketTypeRepository;

    @Autowired
    public SeanceService(SeanceRepository seanceRepository, TicketTypeRepository ticketTypeRepository) {
        this.seanceRepository = seanceRepository;
        this.ticketTypeRepository = ticketTypeRepository;
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
}
