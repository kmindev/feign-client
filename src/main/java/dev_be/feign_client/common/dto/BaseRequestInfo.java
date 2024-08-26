package dev_be.feign_client.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BaseRequestInfo(
        String name,
        Long age
) {

    public static BaseRequestInfo of(String name, Long age) {
        return new BaseRequestInfo(name, age);
    }

}
