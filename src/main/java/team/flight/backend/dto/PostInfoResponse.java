package team.flight.backend.dto;

import team.flight.backend.entity.Post;

public record PostInfoResponse(
        Long postId,
        String request
) {
    public static PostInfoResponse from(Post post) {
        return new PostInfoResponse(post.getId(), post.getRequest());
    }
}
