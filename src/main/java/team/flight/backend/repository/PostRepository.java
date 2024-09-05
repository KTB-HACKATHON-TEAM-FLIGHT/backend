package team.flight.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.flight.backend.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
