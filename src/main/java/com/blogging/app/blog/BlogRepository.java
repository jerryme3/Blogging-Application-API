package com.blogging.app.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

	@Query(value = """
				SELECT * FROM blogs
				WHERE title ILIKE '%' || :toFind || '%'
				OR content ILIKE '%' || :toFind || '%'
				OR category ILIKE '%' || :toFind || '%'
				OR EXISTS (
					SELECT 1
					FROM unnest(tags) AS tag
					WHERE tag ILIKE '%' || :toFind || '%'
				);""", nativeQuery = true)
	List<Blog> search(@Param("toFind") String toFind);

}
