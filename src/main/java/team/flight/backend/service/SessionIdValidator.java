package team.flight.backend.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.flight.backend.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class SessionIdValidator {

    private final UserRepository userRepository;

    public void validateSessionId(UUID sessionId) {
        if (!userRepository.existsBySessionId(sessionId)){
            throw new RuntimeException("");
        }
    }
}
