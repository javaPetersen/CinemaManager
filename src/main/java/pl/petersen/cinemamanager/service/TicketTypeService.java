package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.petersen.cinemamanager.entity.TicketType;
import pl.petersen.cinemamanager.repository.TicketTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;
    private final SeanceService seanceService;

    @Autowired
    public TicketTypeService(TicketTypeRepository ticketTypeRepository, SeanceService seanceService) {
        this.ticketTypeRepository = ticketTypeRepository;
        this.seanceService = seanceService;
    }

    public Optional<TicketType> findById(Long ticketTypeId) {
        return ticketTypeRepository.findById(ticketTypeId);
    }

    public void save(TicketType ticketType) {
        ticketTypeRepository.save(ticketType);
    }

    public List<TicketType> findAll() {
        return ticketTypeRepository.findAll();
    }

    public Boolean deleteById(Long deleteId) {
        long count = seanceService.countByTicketTypesId(deleteId);
        if (count == 0) {
            ticketTypeRepository.deleteById(deleteId);
            return true;
        }
        return false;
    }
}
