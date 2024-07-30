package org.openschool.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openschool.util.LogText;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {

    private final LogText logText;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        long start = System.currentTimeMillis();

        StringWriter logMessage = new StringWriter();
        PrintWriter logWriter = new PrintWriter(logMessage);

        logWriter.println(logText.formatLog(LogText.REQUEST_METHOD, request.getMethod()));
        logWriter.println(logText.formatLog(LogText.REQUEST_URL, request.getRequestURL().toString()));
        logWriter.println(logText.formatLog(LogText.REQUEST_HEADERS,
                logText.formatHeaders(request.getHeaderNames(), request::getHeader)));

        filterChain.doFilter(request, response);

        logWriter.println(logText.formatLog(LogText.RESPONSE_CODE, String.valueOf(response.getStatus())));
        logWriter.println(logText.formatLog(LogText.RESPONSE_HEADERS,
                logText.formatHeaders(Collections.enumeration(response.getHeaderNames()), response::getHeader)));
        logWriter.println(logText.formatLog(LogText.REQUEST_DURATION, System.currentTimeMillis() - start + " ms"));
        logWriter.println("--------------------------------------------------------------------------------------");

        log.info(logMessage.toString());
    }
}


