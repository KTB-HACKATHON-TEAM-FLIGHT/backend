package team.flight.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import team.flight.backend.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndSessionId(Long postId, UUID sessionId);

    List<Post> findAllBySessionId(UUID sessionId);
}
