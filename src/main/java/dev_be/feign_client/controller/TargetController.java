package dev_be.feign_client.controller;

import dev_be.feign_client.common.dto.BaseRequestInfo;
import dev_be.feign_client.common.dto.BaseResponseInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/target_server")
@RestController
public class TargetController {

    @GetMapping("/get")
    public BaseResponseInfo demoGet(
            @RequestHeader("CustomHeaderName") String header,
            @RequestParam("name") String name,
            @RequestParam("age") Long age
    ) {
        return BaseResponseInfo.of(header, name, age);
    }

    @PostMapping("/post")
    public BaseResponseInfo demoPost(
            @RequestHeader("CustomHeaderName") String header,
            @RequestBody BaseRequestInfo baseRequestInfo
    ) {
        return BaseResponseInfo.of(header, baseRequestInfo.name(), baseRequestInfo.age());
    }

    @GetMapping("/error")
    public ResponseEntity<BaseRequestInfo> demoErrorDecoder() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
