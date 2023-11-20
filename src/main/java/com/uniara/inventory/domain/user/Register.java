package com.uniara.inventory.domain.user;

import com.uniara.inventory.domain.dealer.Dealer;

public record Register(String login, String password, UserRole role, String cod) {
}