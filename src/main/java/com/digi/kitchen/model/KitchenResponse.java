package com.digi.kitchen.model;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KitchenResponse extends KitchenTicketDetails {

    private String message;
}
