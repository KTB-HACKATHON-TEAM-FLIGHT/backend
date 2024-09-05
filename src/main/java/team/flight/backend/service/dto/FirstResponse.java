package team.flight.backend.service.dto;

import team.flight.backend.entity.Post;

public record FirstResponse(
        Long postId,
        String result
) {
    public static FirstResponse from(Post post){
        return new FirstResponse(post.getId(), post.getResult());
    }
}
