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
    public FirstResponse sendUserFirstRequest(UUID sessionId, String request) {
        Post post = Post.builder()
                .sessionId(sessionId)
                .request(request)
                .build();
        postRepository.save(post);

        post.updateResult(webClientSender.sendFirstRequest(request));

        return FirstResponse.from(post);
    }
}
