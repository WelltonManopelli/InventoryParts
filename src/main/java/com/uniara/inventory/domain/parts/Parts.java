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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "partnumber")
    private String partNumber;
    private Integer quantity;
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "dealer_cod")
    private Dealer dealer;

    public Parts (PartsRequest partsRequest, Dealer dealer ){

        this.partNumber = partsRequest.partNumber();
        this.quantity = partsRequest.quantity();
        this.value = partsRequest.value();
        this.dealer = dealer;
    }

}





