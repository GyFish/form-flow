package com.gyfish.api.client;

import com.gyfish.api.controller.vo.FormVo;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

/**
 * @author geyu
 */
@Component
@Slf4j
public class FormClient {

    private WebClient client = WebClient.builder()
            .baseUrl("http://localhost:7002")
            .build();

    public void saveFormMeta(FormVo formVo) {

        log.info(">> saveFormMeta");

        client.post()
                .uri("/form/saveFormMeta")
                .syncBody(formVo)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(log::info);
    }

}
