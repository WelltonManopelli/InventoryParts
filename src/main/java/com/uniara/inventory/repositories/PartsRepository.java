package com.uniara.inventory.repositories;

import com.uniara.inventory.domain.dealer.Dealer;
import com.uniara.inventory.domain.parts.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PartsRepository extends JpaRepository<Parts, Long> {
    List<Parts> findAllByPartNumber(String partNumber);
 /*   Parts findByPartNumber (String partNumber, String Dealer);*/
    List<Parts> findAllByDealerCod (String Dealercod);

    @Query("SELECT p FROM Parts p WHERE p.partNumber = :partNumber AND p.dealer = :dealer")
    Parts findByPartNumberAndDealer(@Param("partNumber") String partNumber, @Param("dealer") Dealer dealer);

    @Modifying
    @Query("DELETE FROM Parts p WHERE p.partNumber = :partNumber AND p.dealer = :dealer")
    void deleteByPartNumberAndDealer(@Param("partNumber") String partNumber, @Param("dealer") Dealer dealer);


}
