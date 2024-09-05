package team.flight.backend.dto;

import java.util.List;
import java.util.UUID;

public record PostsResponse(
        UUID sessionId,
        List<PostInfoResponse> posts
) {
}
