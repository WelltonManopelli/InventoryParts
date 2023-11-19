package com.uniara.inventory.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Login(@NotBlank String login, @NotNull String password) {
}
