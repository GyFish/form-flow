package com.gyfish.api.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

/**
 * @author geyu
 */
@Component
public class ManagerClient {

    private WebClient client = WebClient.builder()
            .baseUrl("http://localhost:7001/manager")
            .build();

    public Mono<String> saveDefinition(Object o) {

        return client.post()
                .uri("/form/saveDefinition")
                .syncBody(o)
                .retrieve()
                .bodyToMono(String.class);
    }

}
