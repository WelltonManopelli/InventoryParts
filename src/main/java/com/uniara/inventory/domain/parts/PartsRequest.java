package com.uniara.inventory.domain.parts;

import com.uniara.inventory.domain.dealer.Dealer;

import java.math.BigDecimal;

public record PartsRequest(Long id, String partNumber, Integer quantity, BigDecimal value, Dealer dealer) {
}
