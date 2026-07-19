package com.url.model.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record UrlDTO(
          UUID id,
          String url,
          String ShortCode,
          Timestamp createdAt,
          Timestamp updatedAt,
          int accessCount
) {
}
