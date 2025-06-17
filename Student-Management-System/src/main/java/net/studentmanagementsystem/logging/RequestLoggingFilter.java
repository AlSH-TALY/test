package net.studentmanagementsystem.logging;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@WebFilter("/*")
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(httpRequest);

        chain.doFilter(wrappedRequest, response);

        logRequest(wrappedRequest);
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        StringBuilder sb = new StringBuilder();

        // Log method and URL
        sb.append("Request: ").append(request.getMethod()).append(" ").append(request.getRequestURI()).append("\n");

        // Log headers
        Collections.list(request.getHeaderNames()).forEach(headerName ->
            sb.append(headerName).append(": ").append(request.getHeader(headerName)).append("\n")
        );

        // Log JSON body
        byte[] content = request.getContentAsByteArray();
        if (content.length > 0) {
            String body = new String(content, StandardCharsets.UTF_8);
            sb.append("Body: ").append(body).append("\n");
        }

        System.out.println(sb);
    }
}
