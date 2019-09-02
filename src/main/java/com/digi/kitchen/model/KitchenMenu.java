package com.digi.kitchen.model;

import com.digi.kitchen.config.MenuType;
import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class KitchenMenu {

    private String menuId;
    private String menuName;
    private String description;
    private BigDecimal price;
    private MenuType type;

}
