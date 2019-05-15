package com.gyfish.api.client;

import com.gyfish.api.client.vo.FormInfo;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @author geyu
 */
@Component
@Slf4j
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

    /**
     * mysql 插入数据，返回 uuid
     */
    public void saveFormInfo(FormInfo info) {

        log.info(">> saveFormInfo");

        client.post()
                .uri("/form/saveFormInfo")
                .syncBody(info)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(log::info);
    }
}
