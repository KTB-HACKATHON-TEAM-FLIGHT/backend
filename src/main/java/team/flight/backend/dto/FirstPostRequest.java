package team.flight.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record FirstPostRequest(
        @Schema(description = "존재하는 클라이언트 session-id")
        String sessionId,
        @Schema(description = "존재하는 게시글 ID, 없으면 null", nullable = true)
        Long postId,
        @Schema(description = "사용자의 요청 프롬프트")
        String request
) {
}
