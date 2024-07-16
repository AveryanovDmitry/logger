package org.openschool.util;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class LogText {
    public static final String REQUEST_METHOD = "Request method";
    public static final String REQUEST_URL = "Request URL";
    public static final String REQUEST_HEADERS = "Request headers";
    public static final String RESPONSE_CODE = "Code response";
    public static final String RESPONSE_HEADERS = "Headers response";
    public static final String REQUEST_DURATION = "Request duration, ms";

    public String formatHeaders(Enumeration<String> headerNames, Function<String, String> headerProvider) {
        return Collections.list(headerNames).stream()
                .map(headerName -> headerName + ": " + headerProvider.apply(headerName))
                .collect(Collectors.joining("; "));
    }

    public String formatHeaders(Map<String, Collection<String>> headers) {
        return headers.entrySet().stream().map(entry -> entry.getKey()
                        + ": " + String.join(", ", entry.getValue()))
                .collect(Collectors.joining("\n"));
    }

    public String formatLog(String key, String value) {
        return key + ": " + value;
    }

}
