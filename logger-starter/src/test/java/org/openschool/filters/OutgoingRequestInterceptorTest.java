package org.openschool.filters;

import static org.mockito.Mockito.*;

import feign.RequestTemplate;
import org.junit.jupiter.api.Test;
import org.openschool.util.LogText;

public class OutgoingRequestInterceptorTest {

    @Test
    public void testApply() {
        LogText logText = mock(LogText.class);
        RequestTemplate requestTemplate = mock(RequestTemplate.class);
        when(requestTemplate.method()).thenReturn("GET");
        when(requestTemplate.url()).thenReturn("http://example.com");

        OutgoingRequestInterceptor interceptor = new OutgoingRequestInterceptor(logText);

        interceptor.apply(requestTemplate);

        verify(logText, times(1)).formatLog(eq(LogText.REQUEST_METHOD), eq("GET"));
        verify(logText, times(1)).formatLog(eq(LogText.REQUEST_URL), eq("http://example.com"));
        verify(logText, times(1)).formatLog(eq(LogText.REQUEST_HEADERS), any());
        verify(logText, times(1)).formatLog(eq(LogText.REQUEST_DURATION), anyString());
    }
}