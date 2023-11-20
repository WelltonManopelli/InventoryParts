package com.uniara.inventory.domain.parts;

import com.uniara.inventory.domain.dealer.Dealer;

import java.math.BigDecimal;

public record PartsRequest(String partNumber, Integer quantity, BigDecimal value) {
}
