package com.cs316.money_mind.filter;

import org.apache.log4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * AccessFilter
 *
 * @author Sainjargal Ishdorj
 **/

@Component
@Order(0)
public class AccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        String uuid = UUID.randomUUID().toString();
//
//        MDC.put("correlation_id", uuid);
//        res.addHeader("X-Correlation-Id",uuid);
        chain.doFilter(request, response);
        MDC.clear();
    }
}
