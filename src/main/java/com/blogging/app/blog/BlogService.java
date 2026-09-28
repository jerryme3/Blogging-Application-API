package com.blogging.app.blog;

import com.blogging.app.blog.dto.BlogRequest;
import com.blogging.app.blog.dto.BlogResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BlogService {

	private final BlogRepository blogRepository;

	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

	public BlogResponse getById(Long id) {
		return blogRepository
				.findById(id)
				.map(b -> new BlogResponse(b.getId(), b.getTitle(), b.getContent(), b.getCategory(), b.getTags(), b.getCreatedAt(), b.getUpdatedAt()))
				.orElseThrow();
	}

	public List<BlogResponse> search(String toFind) {
		if (toFind == null || toFind.isBlank()) throw new IllegalArgumentException("Your search bar is empty!");

		return blogRepository
				.search(toFind)
				.stream()
				.map(b -> new BlogResponse(b.getId(), b.getTitle(), b.getContent(), b.getCategory(), b.getTags(), b.getCreatedAt(), b.getUpdatedAt()))
				.toList();
	}

	public BlogResponse post(BlogRequest request) {
		if (request.tags().isEmpty()) throw new IllegalArgumentException("Tags should be filled with at least 1 tag.");

		var blog = applyRequest(request);

		return toResponse(blogRepository.save(blog));
	}

	//put the separation of concern in the frontend (parang iba-ibang buttons per update...)
	public BlogResponse update(Long id, BlogRequest request) {
		var foundBlog = blogRepository.findById(id);

		if (foundBlog.isEmpty()) throw new NoSuchElementException("There are no blogs with this ID: " + id + ".");

		var updated = foundBlog.get();
		applyRequest(updated, request);

		return toResponse(blogRepository.save(updated));
	}

	public void	delete(Long id) {
		var toDelete = blogRepository.findById(id);

		if (toDelete.isEmpty()) throw new NoSuchElementException("There are no blogs with this ID: " + id + ".");

		blogRepository.delete(toDelete.get());
	}

	private Blog applyRequest(BlogRequest request) {
		return new Blog(request.title(), request.content(), request.category(), request.tags(), Instant.now(), Instant.now());
	}

	private void applyRequest(Blog blog, BlogRequest request) {
		blog.setTitle(request.title());
		blog.setContent(request.content());
		blog.setCategory(request.category());
		blog.setTags(request.tags());
		blog.setUpdatedAt(Instant.now());
	}

	private BlogResponse toResponse(Blog blog) {
		return new BlogResponse(blog.getId(), blog.getTitle(), blog.getContent(), blog.getCategory(), blog.getTags(), blog.getCreatedAt(), blog.getUpdatedAt());
	}
}
