package team.flight.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import team.flight.backend.service.dto.AiSendRequest;
import team.flight.backend.service.dto.AiSendResponse;

@Component
@RequiredArgsConstructor
public class WebClientSender {

    private final WebClient webClient;

    public AiSendResponse sendFirstRequest(String request) {
        return webClient.post()
                .uri("/api/submit")
                .bodyValue(AiSendRequest.from(request))
                .retrieve()
                .bodyToMono(AiSendResponse.class)
                .block();
    }
}
