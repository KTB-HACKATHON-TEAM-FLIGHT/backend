package team.flight.backend.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.flight.backend.entity.User;
import team.flight.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UUID createSessionId() {
        User newUser = User.builder()
                .sessionId(UUID.randomUUID())
                .build();

        userRepository.save(newUser);

        return newUser.getSessionId();
    }
}
