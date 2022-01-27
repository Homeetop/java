package com.topsoft.bvs.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.topsoft.bvs.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

	Optional<Post> findByName(String string);
	Post findByNameAndState(String name, String state);

}
