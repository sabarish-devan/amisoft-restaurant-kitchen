package com.digi.kitchen.model;


import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KitchenResponse {

    public KitchenResponse(String message) {
        this.message = message;
    }

    private String message;
    private List<KitchenTicketDetails> kitchenTicketList;
}
