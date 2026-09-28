package com.blogging.app.blog;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "blogs")
@Entity
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(length = 1000, nullable = false)
	private String content;

	@Column(nullable = false)
	private String category;

	@Column(nullable = false)
	private List<String> tags;

	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	public Blog(String title, String content, String category, List<String> tags, Instant createdAt, Instant updatedAt) {
		this.title = title;
		this.content = content;
		this.category = category;
		this.tags = tags;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
