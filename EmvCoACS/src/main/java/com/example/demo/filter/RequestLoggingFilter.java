package com.example.demo.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;

public class RequestLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if any
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);

        chain.doFilter(wrappedRequest, response);

        logRequestDetails(wrappedRequest);
    }

    @Override
    public void destroy() {
        // Cleanup code, if any
    }

    private void logRequestDetails(ContentCachingRequestWrapper request) throws IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        logger.debug("================================ Start of Request ======================================");
        logger.debug("Endpoint: {}", httpRequest.getRequestURI());
        logger.debug("Method: {}", httpRequest.getMethod());
        logger.info("Headers:");
        httpRequest.getHeaderNames().asIterator()
                .forEachRemaining(headerName ->
                        logger.debug("{}: {}", headerName, httpRequest.getHeader(headerName))
                );

        byte[] body = request.getContentAsByteArray();
        String bodyString = new String(body, StandardCharsets.UTF_8);
        logger.debug("Body: {}", bodyString);
        logger.debug("================================ End of Request ======================================");
    }
}
