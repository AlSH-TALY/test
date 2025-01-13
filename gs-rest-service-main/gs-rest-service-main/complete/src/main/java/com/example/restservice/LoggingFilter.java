package com.example.restservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;


@Component
public class LoggingFilter extends OncePerRequestFilter {

	private static final Logger LOGGER=LoggerFactory.getLogger(LoggingFilter.class);
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ContentCachingRequestWrapper contentCachingRequestWrapper =new ContentCachingRequestWrapper(request);
		
		filterChain.doFilter(contentCachingRequestWrapper, response);
		String requestBody=getStringValue(contentCachingRequestWrapper.getContentAsByteArray(), request.getCharacterEncoding());


		LOGGER.info("ApiLogsFilter: RequestURL : {} ",request.getRequestURL());
		LOGGER.info("ApiLogsFilter: RequestMethod : {} ",request.getMethod());
		LOGGER.info("ApiLogsFilter: RequestAgent : {} ",request.getHeader("User-Agent"));
		LOGGER.info("ApiLogsFilter: Response Code  : {} ",response.getStatus());
		LOGGER.info("ApiLogsFilter: RequestParams name = : {} ",request.getParameterValues("name"));
		//Logger.info();
		
		
		}

	private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
		// TODO Auto-generated method stub
		try {
			return new String(contentAsByteArray,0,contentAsByteArray.length,characterEncoding);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return "";
	}

	
}
