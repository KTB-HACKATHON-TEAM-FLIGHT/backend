package team.flight.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import team.flight.backend.service.dto.AiSendRequest;

@Component
@RequiredArgsConstructor
public class WebClientSender {

    private final WebClient webClient;

    //Todo ai 서버 스펙에 맞게 변경
    public String sendFirstRequest(String request) {
        return webClient.post()
                .bodyValue(AiSendRequest.from(request))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
