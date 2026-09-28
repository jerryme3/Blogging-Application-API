package com.blogging.app.blog;

import com.blogging.app.blog.dto.BlogRequest;
import com.blogging.app.blog.dto.BlogResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/blogs")
public class BlogController {

	private final BlogService blogService;

	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@PostMapping("/post-blog")
	public ResponseEntity<BlogResponse> postBlog(@Valid @RequestBody BlogRequest request) {
		var response = blogService.post(request);

		return ResponseEntity
				.created(URI.create("api/v1/blogs/post" + response.id()))
				.body(response);
	}

	@GetMapping("/getById/{id}")
	public BlogResponse getById(@PathVariable("id") Long id) {
		return blogService.getById(id);
	}

	@GetMapping("/search/{toFind}")
	public List<BlogResponse> searchBlogs(@PathVariable("toFind") String toFind) {
		return blogService.search(toFind);
	}

	@PutMapping("/update-blog/{id}")
	public BlogResponse updateBlog(@PathVariable("id") Long id, @Valid @RequestBody BlogRequest request) {
		return blogService.update(id, request);
	}

	@DeleteMapping("/delete-blog/{id}")
	public ResponseEntity<Void> deleteBlog(@PathVariable("id") Long id) {
		blogService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
