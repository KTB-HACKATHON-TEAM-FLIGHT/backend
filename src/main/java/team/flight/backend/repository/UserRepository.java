package team.flight.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.flight.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
