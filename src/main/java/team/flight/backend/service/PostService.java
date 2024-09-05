package team.flight.backend.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        post.updateResult(webClientSender.sendFirstRequest(request).getResult());

        postRepository.save(post);
        return FirstResponse.from(post);
    }
}
