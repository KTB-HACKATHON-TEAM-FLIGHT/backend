package team.flight.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
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
                .bodyToMono(String.class)
                .block();
    }
}
