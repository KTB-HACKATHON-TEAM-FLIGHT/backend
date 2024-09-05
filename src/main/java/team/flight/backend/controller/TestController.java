package team.flight.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.flight.backend.dto.FirstPostRequest;
import team.flight.backend.service.dto.FirstResponse;

@RestController
@RestControllerAdvice
@RequestMapping("/api/test")
public class TestController {

    @PostMapping
    public ResponseEntity<FirstResponse> testApi(@RequestBody FirstPostRequest request){
        return ResponseEntity.ok().body(new FirstResponse(1L, request.request()));
    }
}
