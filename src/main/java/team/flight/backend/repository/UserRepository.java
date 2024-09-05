package team.flight.backend.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import team.flight.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsBySessionId(UUID sessionId);
}
