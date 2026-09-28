package com.blogging.app.blog.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public record BlogResponse(
		Long id,
		String title,
		String content,
		String category,
		List<String> tags,
		Instant createdAt,
		Instant updatedAt
) {
}
