package com.uniara.inventory.domain.parts;


import com.uniara.inventory.domain.dealer.Dealer;
import com.uniara.inventory.domain.parts.Parts;

import java.math.BigDecimal;

public record PartsResponse(Long id, String partNumber, Integer quantity, BigDecimal value, Dealer dealer) {
        public PartsResponse(Parts parts){
            this(parts.getId(), parts.getPartNumber(), parts.getQuantity(), parts.getValue(), parts.getDealer());
        }

}
