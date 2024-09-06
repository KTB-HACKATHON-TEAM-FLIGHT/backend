package team.flight.backend.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.flight.backend.dto.PostDetailResponse;
import team.flight.backend.dto.PostInfoResponse;
import team.flight.backend.dto.PostsResponse;
import team.flight.backend.entity.Post;
import team.flight.backend.repository.PostRepository;
import team.flight.backend.service.dto.FirstResponse;

@Service
@RequiredArgsConstructor
public class PostService {

    private final WebClientSender webClientSender;
    private final PostRepository postRepository;

    @Transactional
    public FirstResponse sendUserFirstRequest(UUID sessionId, Long postId, String request) {
        Post post = postRepository.findByIdAndSessionId(postId, sessionId).orElseGet(() -> Post.builder()
                .sessionId(sessionId)
                .request(request)
                .build());

        post.updateRequest(request);
        post.updateResult(webClientSender.sendFirstRequest(request));

        postRepository.save(post);
        return FirstResponse.from(post);
    }

    @Transactional(readOnly = true)
    public PostsResponse findAllPostsBy(UUID sessionId) {
        List<PostInfoResponse> response = postRepository.findAllBySessionId(sessionId).stream()
                .map(PostInfoResponse::from)
                .toList();
        return new PostsResponse(sessionId, response);
    }

    @Transactional(readOnly = true)
    public PostDetailResponse findPostsBy(Long postId, UUID sessionId) {
        Post post = postRepository.findByIdAndSessionId(postId, sessionId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));

        return new PostDetailResponse(post.getId(), post.getRequest(), post.getResult());
    }
}
