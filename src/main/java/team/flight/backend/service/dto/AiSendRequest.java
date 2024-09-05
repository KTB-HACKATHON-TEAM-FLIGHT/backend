package team.flight.backend.service.dto;

public record AiSendRequest(
        String request
) {
    public static AiSendRequest from(String request) {
        return new AiSendRequest(request);
    }
}
