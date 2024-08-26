package dev_be.feign_client.controller;

import dev_be.feign_client.common.dto.BaseResponseInfo;
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

}
