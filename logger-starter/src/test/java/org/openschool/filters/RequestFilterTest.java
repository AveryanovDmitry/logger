package org.openschool.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openschool.util.LogText;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RequestFilterTest {

    @Mock
    private LogText logText;

    @InjectMocks
    private RequestFilter requestFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoFilterInternal() throws ServletException, IOException {
        when(logText.formatLog(anyString(), anyString())).thenReturn("formattedLog");
        when(logText.formatHeaders(any(), any())).thenReturn("formattedHeaders");
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://example.com"));

        requestFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);

        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);
        verify(logText, times(6)).formatLog(anyString(), logCaptor.capture());

        assertEquals(6, logCaptor.getAllValues().size());
    }
}