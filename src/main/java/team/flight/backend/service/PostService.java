package team.flight.backend.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
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
        post.updateResult(cleanHtmlString(webClientSender.sendFirstRequest(request).getResponse()));

        postRepository.save(post);
        return FirstResponse.from(post);
    }

    @Transactional(readOnly = true)
    public PostsResponse findAllPostsBy(UUID sessionId) {
        AtomicLong index = new AtomicLong(1);
        List<PostInfoResponse> response = postRepository.findAllBySessionId(sessionId).stream()
                .map(post -> PostInfoResponse.of(post, index.getAndIncrement()))
                .toList();
        return new PostsResponse(sessionId, response);
    }

    @Transactional(readOnly = true)
    public PostDetailResponse findPostsBy(Long postId, UUID sessionId) {
        Post post = postRepository.findByIdAndSessionId(postId, sessionId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));

        return new PostDetailResponse(post.getId(), post.getRequest(), post.getResult());
    }

    public String cleanHtmlString(String rawHtml) {
        // \n, \r, \t 등 불필요한 이스케이프 문자 제거
        return rawHtml
                .replace("\\n", "\n")  // 줄바꿈 처리
                .replace("\\t", "\t")  // 탭 처리
                .replace("\\\"", "\"") // 이중 인용부호 처리
                .replace("\\/", "/")   // 슬래시 처리
                .replace("\\\\", "\\") // 백슬래시 처리
                .replace("<p>```html</p>", "");
    }

}
