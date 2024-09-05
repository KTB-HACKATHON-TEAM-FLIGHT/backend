package team.flight.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.flight.backend.dto.SessionIdResponse;
import team.flight.backend.service.UserService;

@Tag(name = "유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "최초 세션 발급 요청 API", description = "사용자가 최초 세션ID를 발급 받는다.")
    @PostMapping("/session")
    public ResponseEntity<SessionIdResponse> createSessionId() {
        return ResponseEntity.ok().body(SessionIdResponse.from(userService.createSessionId()));
    }
}
