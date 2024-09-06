package team.flight.backend.dto;

import team.flight.backend.entity.Post;

public record PostInfoResponse(
        Long postId,
        Long number,
        String request
) {
    public static PostInfoResponse of(Post post, Long number) {
        return new PostInfoResponse(post.getId(),
                number,
                post.getRequest());
    }
}
