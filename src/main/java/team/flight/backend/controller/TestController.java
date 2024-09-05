package team.flight.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.flight.backend.dto.FirstPostRequest;
import team.flight.backend.service.dto.FirstResponse;

@Tag(name = "TEST 관련 API")
@RestController
@RestControllerAdvice
@RequestMapping("/api/test")
public class TestController {

    @Operation(summary = "TEST 요청 API")
    @PostMapping
    public ResponseEntity<FirstResponse> testApi(@RequestBody FirstPostRequest request){
        return ResponseEntity.ok().body(new FirstResponse(1L, request.request()));
    }

    @Operation(summary = "서버 상태 확인용 API")
    @GetMapping
    public ResponseEntity<String> pingApi(){
        return ResponseEntity.ok().body("OK");
    }
}
