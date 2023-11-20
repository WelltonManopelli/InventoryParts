package com.uniara.inventory.domain.parts;

import com.uniara.inventory.domain.dealer.Dealer;
import jakarta.persistence.*;

import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter

@Entity
public class Parts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String partNumber;
    private int quantity;
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;

    public Parts (PartsRequest partsRequest, Dealer dealer ){

        this.partNumber = partsRequest.partNumber();
        this.quantity = partsRequest.quantity();
        this.dealer = dealer;
    }

}





