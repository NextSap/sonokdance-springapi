package be.sonokdance.api.service.ticket;

import be.sonokdance.api.dao.TicketDao;
import be.sonokdance.api.dto.TicketDto;
import be.sonokdance.api.exception.TicketNotFoundException;
import be.sonokdance.api.repository.TicketRepository;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<TicketDao> findAll() {
        return (List<TicketDao>) ticketRepository.findAll();
    }

    @Override
    public Optional<TicketDao> findById(long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public TicketDao save(TicketDto ticketDto) {
        TicketDao ticketDao = modelMapper.map(ticketDto, TicketDao.class);
        return ticketRepository.save(ticketDao);
    }

    @SneakyThrows
    @Override
    public Optional<TicketDao> update(long id, TicketDto ticketDto) {
        if (ticketRepository.findById(id).isPresent()) {
            TicketDao ticketDao = modelMapper.map(ticketDto, TicketDao.class);
            ticketDao.set_id(id);
            TicketDao savedTicketDao = ticketRepository.save(ticketDao);
            return Optional.of(savedTicketDao);
        } else throw new TicketNotFoundException("Can't found ticket with id: "+id);
    }

    @SneakyThrows
    @Override
    public void deleteById(long id) {
        if (ticketRepository.findById(id).isPresent()) ticketRepository.deleteById(id);
        else throw new TicketNotFoundException("Can't find ticket with id: " + id);
    }
}
