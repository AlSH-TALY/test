package com.example.demo.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

public class RequestLoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if any
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Wrap the request to cache the body
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);

        // Continue the request-response chain
        chain.doFilter(wrappedRequest, response);

        // Log request details after the chain has processed
        logRequestDetails(wrappedRequest);
    }

    @Override
    public void destroy() {
        // Cleanup code, if any
    }

    private void logRequestDetails(ContentCachingRequestWrapper request) throws IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        System.out.println("================================ Start of Request ======================================");
        System.out.println("Request Details:");
        System.out.println("Endpoint: " + httpRequest.getRequestURI());
        System.out.println("Method: " + httpRequest.getMethod());
        System.out.println("Headers: ");
        httpRequest.getHeaderNames().asIterator().forEachRemaining(headerName ->
            System.out.println(headerName + ": " + httpRequest.getHeader(headerName))
        );

        // Read and log the request body
        byte[] body = request.getContentAsByteArray();
        String bodyString = new String(body, StandardCharsets.UTF_8);
        System.out.println("Body: " + bodyString);
        System.out.println("================================ End of Request ======================================");
    }
}
