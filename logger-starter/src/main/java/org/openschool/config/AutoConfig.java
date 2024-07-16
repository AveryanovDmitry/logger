package org.openschool.config;

import lombok.RequiredArgsConstructor;
import org.openschool.filters.OutgoingRequestInterceptor;
import org.openschool.filters.RequestFilter;
import org.openschool.util.LogText;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AutoConfig {

    private final LogText logText;

    @Bean
    public OutgoingRequestInterceptor responseInterceptorInterceptor() {
        return new OutgoingRequestInterceptor(logText);
    }

    @Bean
    public RequestFilter requestFilterFilter() {
        return new RequestFilter(logText);
    }
}
