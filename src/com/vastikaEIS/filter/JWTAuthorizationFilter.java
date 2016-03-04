package com.vastikaEIS.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.vastikaEIS.domain.TokenInfo;
import com.vastikaEIS.serviceImpl.JWTService;


@WebFilter("/Authorize")
public class JWTAuthorizationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public JWTAuthorizationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authHeader = httpRequest.getHeader("Authorization");

		if (authHeader != null) {
			String token = authHeader.replace("Bearer ", "");
			TokenInfo tokenInfo = JWTService.verifyToken(token);
			httpRequest.setAttribute("userId", tokenInfo.getUserId());

			chain.doFilter(request, response);
		} else {
			System.out.println("Error: No Token");
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
