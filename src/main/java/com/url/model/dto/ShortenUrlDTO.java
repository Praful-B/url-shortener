package com.url.model.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record ShortenUrlDTO(
        UUID id,
        String url,
        String shortCode,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
