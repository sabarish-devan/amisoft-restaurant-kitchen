package com.digi.kitchen.service;

import com.digi.kitchen.config.TicketStatus;
import com.digi.kitchen.entity.KitchenTicket;
import com.digi.kitchen.event.KitchenTicketEvent;
import com.digi.kitchen.event.OrderDetails;
import com.digi.kitchen.repo.KitchenTicketRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.digi.kitchen.constant.RabbitmqConstant.KITCHEN_EXCHANGE_NAME;
import static com.digi.kitchen.constant.RabbitmqConstant.KITCHEN_ROUTING_KEY;

@Service
@Slf4j
public class KitchenTicketService {

    @Autowired
    private KitchenTicketRepo kitchenTicketRepo;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public KitchenTicket createTicket(OrderDetails orderDetails){

        log.info("Kitchen ticket creation ");
        KitchenTicket kitchenTicket = new KitchenTicket();
        BeanUtils.copyProperties(orderDetails,kitchenTicket);
        kitchenTicket.setStatus(TicketStatus.New.name());
        KitchenTicket kitchenTicketSaved = kitchenTicketRepo.save(kitchenTicket);

        log.info("Pushing create ticket event");
        pushEvent(kitchenTicketSaved);

        return kitchenTicketSaved;
    }

    private void pushEvent(KitchenTicket kitchenTicketSaved) {
        KitchenTicketEvent kitchenTicketEvent = new KitchenTicketEvent();
        kitchenTicketEvent.setEventType("Create Kitchen Ticket");
        kitchenTicketEvent.setTicketId(kitchenTicketSaved.getTicketId());
        kitchenTicketEvent.setEventId(RandomStringUtils.randomAlphanumeric(10));
        rabbitTemplate.convertAndSend(KITCHEN_EXCHANGE_NAME,KITCHEN_ROUTING_KEY,kitchenTicketEvent);
    }
}
