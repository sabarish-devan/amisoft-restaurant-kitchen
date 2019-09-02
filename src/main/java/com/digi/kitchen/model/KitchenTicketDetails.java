package com.digi.kitchen.model;

import com.digi.kitchen.config.TicketStatus;
import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class KitchenTicketDetails {

    private String ticketId;
    private String userId;
    private String orderId;
    private BigDecimal orderValue;
    private TicketStatus status;
}
