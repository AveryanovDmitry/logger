package org.openschool.filters;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openschool.util.LogText;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@RequiredArgsConstructor
public class OutgoingRequestInterceptor implements RequestInterceptor {

    private final LogText logText;

    private final Map<String, String> logMessages = new HashMap<>();

    @Override
    public void apply(RequestTemplate template) {
        long start = System.currentTimeMillis();

        StringWriter logMessage = new StringWriter();
        PrintWriter logWriter = new PrintWriter(logMessage);

        logWriter.println(logText.formatLog(LogText.REQUEST_METHOD, template.method()));
        logWriter.println(logText.formatLog(LogText.REQUEST_URL, template.url()));
        logWriter.println(logText.formatLog(LogText.REQUEST_HEADERS,
                logText.formatHeaders(template.headers())));
        logWriter.println(logText.formatLog(LogText.REQUEST_DURATION, System.currentTimeMillis() - start + " ms"));
        logWriter.println("--------------------------------------------------------------------------------------");
        log.info(logMessage.toString());
    }

}