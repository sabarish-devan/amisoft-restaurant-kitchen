package com.digi.kitchen.event;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CustomerOrder {

    @NonNull
    private String customerId;
    @NonNull
    private String orderedMenu;
}
