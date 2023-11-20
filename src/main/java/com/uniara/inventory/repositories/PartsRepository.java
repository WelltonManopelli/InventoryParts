package com.uniara.inventory.repositories;

import com.uniara.inventory.domain.dealer.Dealer;
import com.uniara.inventory.domain.parts.Parts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PartsRepository extends JpaRepository<Parts, Long> {
    List<Parts> findAllByPartNumber(String partNumber);
 /*   Parts findByPartNumber (String partNumber, String Dealer);*/
    List<Parts> findAllByDealerCod (String cod);

 /*   Parts deleteByPartNumber (String partNumber, String Dealer);*/


}
