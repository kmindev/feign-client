package dev_be.feign_client.service;

import dev_be.feign_client.feign.client.DemoFeignClient;
import dev_be.feign_client.common.dto.BaseResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DemoService {

    private final DemoFeignClient demoFeignClient;

    public String get() {
        ResponseEntity<BaseResponseInfo> response = demoFeignClient.callGet("CustomHeader", "홍길동", 1L);
        System.out.println("response.getBody().name() = " + response.getBody().name());
        System.out.println("response.getBody().age() = " + response.getBody().age());
        System.out.println("response.getBody().header() = " + response.getBody().header());
        return "get";
    }
}
