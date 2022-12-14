package be.sonokdance.api.controller;

import be.sonokdance.api.dao.TicketDao;
import be.sonokdance.api.dto.TicketDto;
import be.sonokdance.api.exception.TicketNotFoundException;
import be.sonokdance.api.service.ticket.TicketService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<TicketDao>> getTickets() {
        List<TicketDao> ticketList = ticketService.findAll();
        return new ResponseEntity<>(ticketList, new HttpHeaders(), HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<TicketDao> getTicket(@PathVariable long id) {
        return ticketService.findById(id).map(ResponseEntity::ok)
                .orElseThrow(TicketNotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<TicketDao> addTicket(@RequestBody TicketDto ticketDto) throws ParseException {
        String date = ticketDto.getEventDate();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String parsedDate = String.valueOf(inputFormat.parse(date).getTime());
        ticketDto.setEventDate(parsedDate);
        return new ResponseEntity<>(ticketService.save(ticketDto), HttpStatus.CREATED);
    }

    @SneakyThrows
    @PatchMapping("/{id}")
    public ResponseEntity<TicketDao> updateTicket(@PathVariable long id, @RequestBody TicketDto ticketDto) {
        String date = ticketDto.getEventDate();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String parsedDate = String.valueOf(inputFormat.parse(date).getTime());
        ticketDto.setEventDate(parsedDate);
        return ticketService.update(id, ticketDto).map(ResponseEntity::ok)
                .orElseThrow(TicketNotFoundException::new);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable long id) {
        ticketService.deleteById(id);
    }
}
