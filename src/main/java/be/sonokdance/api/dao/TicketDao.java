package be.sonokdance.api.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "sk_ticket")
public class TicketDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long _id;
    private String dj;
    private long eventDate;
    private String eventName;
    private String eventDescription;
    private String customerName;
    private String phoneNumber;
    private String eventLocation;
}
