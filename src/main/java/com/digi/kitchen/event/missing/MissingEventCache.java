package com.digi.kitchen.event.missing;

import com.digi.kitchen.event.OrderDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MissingEventCache {

   private Map<String, OrderDetails> missingOrderMenuCache = new HashMap<>();

   public void addEvent(OrderDetails orderDetails){
       missingOrderMenuCache.put(orderDetails.getCustomerId(), orderDetails);
   }

   public OrderDetails getEvent(String customerId){
       return missingOrderMenuCache.get(customerId);
   }

   public void removeEvent(String customerId){
       missingOrderMenuCache.remove(customerId);
   }

}
