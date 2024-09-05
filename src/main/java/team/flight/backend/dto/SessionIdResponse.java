package team.flight.backend.dto;

import java.util.UUID;

public record SessionIdResponse(
        UUID sessionId
) {
    public static SessionIdResponse from(UUID sessionId) {
        return new SessionIdResponse(sessionId);
    }
}
