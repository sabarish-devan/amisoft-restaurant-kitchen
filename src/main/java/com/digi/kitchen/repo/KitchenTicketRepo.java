package com.digi.kitchen.repo;

import com.digi.kitchen.entity.KitchenTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenTicketRepo extends JpaRepository<KitchenTicket,Long> {
}
