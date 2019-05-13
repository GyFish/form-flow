package com.gyfish.api.client;

import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

/**
 * @author geyu
 */
@Component
public class FormClient {

    private WebClient client = WebClient.builder()
            .baseUrl("http://localhost:7002")
            .build();

    public Mono<Object> saveFormMeta(Mono<String> meta) {

        return client.post()
                .uri("/form/saveFormMeta")
                .body(meta, String.class)
                .retrieve()
                .bodyToMono(Object.class);
    }

}
