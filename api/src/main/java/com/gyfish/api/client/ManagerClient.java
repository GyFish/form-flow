package com.gyfish.api.client;

import com.alibaba.fastjson.JSON;
import com.gyfish.api.client.vo.FormInfo;
import com.gyfish.api.util.AppResponse;

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

    /**
     * mysql 插入数据，返回 uuid
     */
    public Mono<String> saveFormInfo(FormInfo info) {

        return client.post()
                .uri("/form/saveFormInfo")
                .syncBody(info)
                .retrieve()
                .bodyToMono(AppResponse.class)
                .map(AppResponse::getData)
                .map(JSON::toJSONString)
                .map(json -> JSON.parseObject(json, FormInfo.class))
                .map(FormInfo::getUuid);
    }
}
