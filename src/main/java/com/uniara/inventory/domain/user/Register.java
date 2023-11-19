package com.uniara.inventory.domain.user;

public record Register(String login, String password, UserRole role) {
}