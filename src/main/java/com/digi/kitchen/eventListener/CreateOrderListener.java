package com.digi.kitchen.eventListener;

import com.digi.kitchen.event.OrderDetails;
import com.digi.kitchen.event.missing.MissingEventCache;
import com.digi.kitchen.service.KitchenTicketService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class CreateOrderListener {

    @Autowired
    KitchenTicketService kitchenTicketService;
    @Autowired
    MissingEventCache missingEventCache;

    public void receiveOrder(byte[] message) {

        String massageFromRabbitMQ = new String(message);
        log.info("Received  :" + massageFromRabbitMQ);
        if (StringUtils.isNotEmpty(massageFromRabbitMQ)) {
            Gson gson = new Gson();
            Optional<OrderDetails> orderEvent = Optional.of(gson.fromJson(massageFromRabbitMQ, OrderDetails.class));
            if (orderEvent.isPresent()) {
                if(StringUtils.isEmpty(orderEvent.get().getOrderedMenu())){
                    log.info("Check if menu details present");
                    missingEventCache.addEvent(orderEvent.get());
                    log.info("Information missing hence event details added in cache");
                }else{

                    log.info("Check if the event is already exist in missing information cache");
                    OrderDetails orderDetailsFromCache = missingEventCache.getEvent(orderEvent.get().getCustomerId());
                    if(null != orderDetailsFromCache){
                        log.info("Previous incomplete event found, need to set menu from the current event");
                        orderDetailsFromCache.setOrderedMenu(orderEvent.get().getOrderedMenu());
                        log.info("Missing data of previous event is restored ");
                        kitchenTicketService.createTicket(orderDetailsFromCache);
                    }else{
                        kitchenTicketService.createTicket(orderEvent.get());
                    }
                }

            } else {
                log.info("Order event is ignored");
            }

        }
    }
}
