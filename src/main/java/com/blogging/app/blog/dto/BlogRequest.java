package com.blogging.app.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;

public record BlogRequest(

		@NotBlank(message = "Title is required.")
		String title,

		@NotBlank(message = "Content is required.")
		String content,

		@NotBlank(message = "Category is required.")
		@Size(max = 1000, message = "You are only limited to 1000 of characters.")
		String category,

		@NotNull(message = "Tags are required.")
		List<String> tags
) {
}
