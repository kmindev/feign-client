package dev_be.feign_client.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BaseResponseInfo(
        String name,
        Long age,
        String header
) {
    public static BaseResponseInfo of(String header, String name, Long age) {
        return new BaseResponseInfo(name, age, header);
    }
}
