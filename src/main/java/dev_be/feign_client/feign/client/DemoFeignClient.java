package dev_be.feign_client.feign.client;

import dev_be.feign_client.common.dto.BaseRequestInfo;
import dev_be.feign_client.common.dto.BaseResponseInfo;
import dev_be.feign_client.feign.config.DemoFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "demo-client",
        url = "${spring.cloud.openfeign.url.prefix}",
        configuration = DemoFeignConfig.class
)
public interface DemoFeignClient {

    @GetMapping("/get")
    ResponseEntity<BaseResponseInfo> callGet(
            @RequestHeader("CustomHeaderName") String customHeader,
            @RequestParam("name") String name,
            @RequestParam("age") Long age
    );

    @PostMapping("/post")
    ResponseEntity<BaseResponseInfo> callPost(
            @RequestHeader("CustomHeaderName") String customHeader,
            @RequestBody BaseRequestInfo baseRequestInfo
    );

}
