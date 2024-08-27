package dev_be.feign_client.controller;

import dev_be.feign_client.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DemoController {

    private final DemoService demoService;

    @GetMapping("/get")
    public String getController() {
        return demoService.get();
    }

    @PostMapping("/post")
    public String postController() {
        return demoService.post();
    }

    @GetMapping("/error-decoder")
    public String errorController() {
        return demoService.errorDecoder();
    }

}
