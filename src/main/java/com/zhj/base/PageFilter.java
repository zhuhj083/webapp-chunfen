package com.zhj.base;

import com.zhj.util.ServletUtil;
import com.zhj.util._;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(value = 1)
@WebFilter(filterName = "PageFilter",urlPatterns = "/*")
public class PageFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger("OP");
    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        long start = System.currentTimeMillis();
        filterChain.doFilter(req, resp);

        opLog(start, req);
    }

    public void init(FilterConfig config) throws ServletException {

    }

    private static void opLog(long start, HttpServletRequest req) {
        long end = System.currentTimeMillis();
        String url = ServletUtil.getUrl(req).replace(",", "%2c");
        String ip = ServletUtil.getIp(req);
        int cost = (int) (end - start);
        String log = "[Sogou-Observer,type=chunfen,cost={},url={},ip={},ua={},Owner=OP]";
        logger.info(log, cost, url, ip, ua(req));
    }


    public static String ua(HttpServletRequest req) {
        String ua = req.getHeader("User-Agent");
        if (_.isEmpty(ua)) ua = "-";
        return ua;
    }
}
