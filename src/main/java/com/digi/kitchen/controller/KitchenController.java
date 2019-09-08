package com.digi.kitchen.controller;


import com.digi.kitchen.model.KitchenMenu;
import com.digi.kitchen.model.KitchenResponse;
import com.digi.kitchen.model.KitchenTicketDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchen")
@CrossOrigin(value = "*")
public class KitchenController {

    @PostMapping(path = "/createKitchenTicket")
    public ResponseEntity<KitchenResponse> createKitchenTicket(@RequestBody KitchenTicketDetails orderTicket){

        return null;
    }

    @PutMapping(path = "/approveKitchenTicket")
    public ResponseEntity<KitchenResponse> approveKitchenTicket(@RequestBody KitchenTicketDetails orderTicket){

        return null;
    }

    @PutMapping(path = "/updateKitchenTicketStatus")
    public ResponseEntity<KitchenResponse> updateKitchenTicketStatus(@RequestBody KitchenTicketDetails orderTicket){

        return null;
    }

    @GetMapping("/findAllOrderTicket")
    public ResponseEntity<List<KitchenTicketDetails>> getAllOrderTicket (@RequestParam(value="type", defaultValue = "all") String ticketType) {

        return null;
    }

    @GetMapping("/getAvailableMenu")
    public ResponseEntity<List<KitchenMenu>> getAllAvailableMenu (@RequestParam(value="type", defaultValue = "all") String menuType) {

        return null;
    }

    @GetMapping("/getAllKitchenTicket")
    public ResponseEntity<KitchenResponse> getAllKitchenTicket () {

        return new ResponseEntity<>(new KitchenResponse("Form Kitchen ticket"), HttpStatus.OK);
    }

}
