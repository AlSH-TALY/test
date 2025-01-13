package com.example.demo.filter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.util.*;

public class CustomHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private final Map<String, List<String>> headers = new HashMap<>();

    public CustomHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void setHeader(String name, String value) {
        headers.computeIfAbsent(name, k -> new ArrayList<>()).add(value);
        super.setHeader(name, value);
    }

    @Override
    public void addHeader(String name, String value) {
        headers.computeIfAbsent(name, k -> new ArrayList<>()).add(value);
        super.addHeader(name, value);
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }
}
