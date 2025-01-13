package com.example.demo.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class ResponseLoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if any
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Wrap the response to cache the body
        CustomHttpServletResponseWrapper customResponseWrapper = new CustomHttpServletResponseWrapper((HttpServletResponse) response);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(customResponseWrapper);

        // Continue the request-response chain
        
        
        //String jsonBody = request.getReader().readLine().toString();

        
        
        wrappedResponse.addHeader("X-Request-ID",response.getCharacterEncoding());
        chain.doFilter(request, wrappedResponse);

        // Log response details after the chain has processed
        logResponseDetails(wrappedResponse, customResponseWrapper);

        // Copy the content of the wrapper to the original response
        wrappedResponse.copyBodyToResponse();
    }

    @Override
    public void destroy() {
        // Cleanup code, if any
    }

    private void logResponseDetails(ContentCachingResponseWrapper wrappedResponse, CustomHttpServletResponseWrapper customResponseWrapper) throws IOException {
        System.out.println("================================ Start of Response ======================================");
        System.out.println("Response Details:");
        System.out.println("Status: " + wrappedResponse.getStatus());

        // Log headers from the custom response wrapper
        System.out.println("Headers:");
        customResponseWrapper.getHeaders().forEach((headerName, headerValues) -> 
            headerValues.forEach(headerValue -> System.out.println(headerName + ": " + headerValue))
        );

        // Read and log the response body
        byte[] body = wrappedResponse.getContentAsByteArray();
        String bodyString = new String(body, StandardCharsets.UTF_8);
        System.out.println("Body: " + bodyString);
        System.out.println("================================ End of Response ======================================");
    }
}
