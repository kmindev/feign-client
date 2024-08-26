package dev_be.feign_client.feign.interceptor;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

@RequiredArgsConstructor
public class DemoFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {

        // GET 요청일 경우
        if (Objects.equals(requestTemplate.method(), Request.HttpMethod.GET.name())) {
            System.out.println("[GET] [DemoFeignInterceptor] queries : " + requestTemplate.queries());
            return;
        }

        // POST 요청일 경우
        if (Objects.equals(requestTemplate.method(), Request.HttpMethod.POST.name())) {
            String encodedRequestBody = StringUtils.toEncodedString(requestTemplate.body(), UTF_8);
            System.out.println("[POST] [DemoFeignInterceptor] requestBody : " + encodedRequestBody);
        }

        // Do Something
        // header, body 값 수정 등
    }

}
