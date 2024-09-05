package team.flight.backend.dto;

public record FirstPostRequest(
        String sessionId,
        Long postId,
        String request
) {
}
