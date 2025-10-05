package com.example.demo.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class ResponseLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if any
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Wrap the response to allow content caching
        CustomHttpServletResponseWrapper customResponseWrapper =
                new CustomHttpServletResponseWrapper((HttpServletResponse) response);
        ContentCachingResponseWrapper wrappedResponse =
                new ContentCachingResponseWrapper(customResponseWrapper);

        // Add custom header if needed
        wrappedResponse.addHeader("X-Request-ID", response.getCharacterEncoding());

        // Continue filter chain
        chain.doFilter(request, wrappedResponse);

        // Log response details after processing
        logResponseDetails(wrappedResponse, customResponseWrapper);

        // Copy cached content back to actual response
        wrappedResponse.copyBodyToResponse();
    }

    @Override
    public void destroy() {
        // Cleanup code, if any
    }

    private void logResponseDetails(ContentCachingResponseWrapper wrappedResponse,
                                    CustomHttpServletResponseWrapper customResponseWrapper)
            throws IOException {

        logger.info("================================ Start of Response ======================================");
        logger.info("Response Details:");
        logger.info("Status: {}", wrappedResponse.getStatus());

        logger.info("Headers:");
        customResponseWrapper.getHeaders().forEach((headerName, headerValues) ->
                headerValues.forEach(headerValue ->
                        logger.debug("{}: {}", headerName, headerValue)
                )
        );

        // Read and log the response body
        byte[] body = wrappedResponse.getContentAsByteArray();
        if (body.length > 0) {
            String bodyString = new String(body, StandardCharsets.UTF_8);
            logger.debug("Body: {}", bodyString);
        } else {
            logger.debug("Body: [empty]");
        }

        logger.info("================================ End of Response ======================================");
    }
}
