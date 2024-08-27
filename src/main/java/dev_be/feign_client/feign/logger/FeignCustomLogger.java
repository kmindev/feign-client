package dev_be.feign_client.feign.logger;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;

import java.io.IOException;
import java.util.Iterator;

public class FeignCustomLogger extends Logger {

    private static final int DEFAULT_SLOW_API_TIME = 3_000;
    private static final String SLOW_API_NOTICE = "Slow API";

    @Override
    protected void log(String configKey, String format, Object... args) {
        // log를 어떤 형식으로 남길지 정해준다.
        System.out.println(String.format(methodTag(configKey) + format, args));
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        /**
         * [값]
         * configKey = DemoFeignClient#callGet(String,String,Long)
         * logLevel = BASIC # "feign.client.config.demo-client.loggerLevel" 참고
         *
         * [동작 순서]
         * `logRequest` 메소드 진입 -> 외부 요청 -> `logAndRebufferResponse` 메소드 진입
         *
         * [참고]
         * request에 대한 정보는
         * `logAndRebufferResponse` 메소드 파라미터인 response에도 있다.
         * 그러므로 request에 대한 정보를 [logRequest, logAndRebufferResponse] 중 어디에서 남길지 정하면 된다.
         * 만약 `logAndRebufferResponse`에서 남긴다면 `logRequest`는 삭제해버리자.
         */
        System.out.println(request);
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        String protocolVersion = resolveProtocolVersion(response.protocolVersion());
        String reason = response.reason() != null && logLevel.compareTo(Logger.Level.NONE) > 0 ? " " + response.reason() : "";
        int status = response.status();
        this.log(configKey, "<--- %s %s%s (%sms)", protocolVersion, status, reason, elapsedTime);
        if (logLevel.ordinal() >= Logger.Level.HEADERS.ordinal()) {
            Iterator<String> var9 = response.headers().keySet().iterator();

            while (true) {
                String field;
                do {
                    if (!var9.hasNext()) {
                        int bodyLength = 0;
                        if (response.body() != null && status != 204 && status != 205) {
                            if (logLevel.ordinal() >= Logger.Level.FULL.ordinal()) {
                                this.log(configKey, "");
                            }

                            byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                            bodyLength = bodyData.length;
                            if (logLevel.ordinal() >= Logger.Level.FULL.ordinal() && bodyLength > 0) {
                                this.log(configKey, "%s", Util.decodeOrDefault(bodyData, Util.UTF_8, "Binary data"));
                            }

                            if (elapsedTime > DEFAULT_SLOW_API_TIME) {
                                this.log(configKey, "[%s] elapsedTime :%s", SLOW_API_NOTICE, elapsedTime);
                            }

                            this.log(configKey, "<--- END HTTP (%s-byte body)", bodyLength);
                            return response.toBuilder().body(bodyData).build();
                        }

                        this.log(configKey, "<--- END HTTP (%s-byte body)", bodyLength);
                        return response;
                    }

                    field = var9.next();
                } while (!this.shouldLogResponseHeader(field));

                for (String value : Util.valuesOrEmpty(response.headers(), field)) {
                    this.log(configKey, "%s: %s", field, value);
                }
            }
        } else {
            return response;
        }
    }

}
