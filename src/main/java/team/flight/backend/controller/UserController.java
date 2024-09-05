package team.flight.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.flight.backend.dto.SessionIdResponse;
import team.flight.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<SessionIdResponse> createSessionId() {
        return ResponseEntity.ok().body(SessionIdResponse.from(userService.createSessionId()));
    }
}
