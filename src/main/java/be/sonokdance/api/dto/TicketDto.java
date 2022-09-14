package be.sonokdance.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private String dj;
    private String eventDate;
    private String eventName;
    private String eventDescription;
    private String customerName;
    private String phoneNumber;
    private String eventLocation;
}
