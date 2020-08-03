package develop.site.repository;

import develop.site.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findFirstByOrderByPostedOnDesc();

    List<Post> findAllByOrderByPostedOnDesc();

    Post findBySlug(String slug);

    List<Post> findAllByAuthorIdOrderByPostedOnDesc(Long id);


}