package be.sonokdance.api.repository;

import be.sonokdance.api.dao.TicketDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketDao, Long> {
}
