package team.flight.backend.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.flight.backend.dto.FirstPostRequest;
import team.flight.backend.service.PostService;
import team.flight.backend.service.dto.FirstResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<FirstResponse> sendFirstRequest(@RequestBody FirstPostRequest request) {
        return ResponseEntity.ok()
                .body(postService.sendUserFirstRequest(UUID.fromString(request.sessionId()), request.request()));
    }
}
