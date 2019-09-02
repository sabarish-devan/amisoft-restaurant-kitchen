package com.digi.kitchen.event;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class KitchenTicketEvent {

    private String eventId;
    private String eventType;
    private Long ticketId;
    private String ticketStatus;
}
