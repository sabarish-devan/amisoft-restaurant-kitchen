package com.digi.kitchen.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="t_restaurant_kitchen")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class KitchenTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ticketId;
    private String customerId;
    private String orderedMenu;
    private String status;

}
