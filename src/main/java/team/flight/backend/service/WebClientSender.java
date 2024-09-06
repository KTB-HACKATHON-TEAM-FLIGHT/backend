package team.flight.backend.service;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import team.flight.backend.global.exception.AppException;
import team.flight.backend.global.exception.ErrorCode;
import team.flight.backend.service.dto.AiSendRequest;

@Component
@RequiredArgsConstructor
public class WebClientSender {

    private final WebClient webClient;

    public String sendFirstRequest(String request) {
        return webClient.post()
                .uri("/api/ppt")
                .bodyValue(AiSendRequest.from(request))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        response -> Mono.error(() -> new AppException(ErrorCode.WEBCLIENT_SERVER_ERROR)))
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(() -> new AppException(ErrorCode.WEBCLIENT_CLIENT_ERROR)))
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(30))
                .block();
    }
}
