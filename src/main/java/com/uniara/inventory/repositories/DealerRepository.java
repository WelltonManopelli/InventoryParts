package com.uniara.inventory.repositories;

import com.uniara.inventory.domain.dealer.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DealerRepository extends JpaRepository<Dealer, Long>  {
}
