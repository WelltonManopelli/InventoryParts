package com.uniara.inventory.domain.dealer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "cod")
@Entity
public class Dealer {
    @Id
    private String cod;
    private String name;
    private String address;

    public Dealer(DealerDTO dealer){
        this.name = dealer.name();
        this.cod = dealer.cod();
        this.address = dealer.address();
    }

    public Dealer() {

    }

}