package be.sonokdance.api.service.ticket;

import be.sonokdance.api.dao.TicketDao;
import be.sonokdance.api.dto.TicketDto;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<TicketDao> findAll();

    Optional<TicketDao> findById(long id);

    TicketDao save(TicketDto ticketDto);

    Optional<TicketDao> update(long id, TicketDto ticketDto);

    void deleteById(long id);
}
