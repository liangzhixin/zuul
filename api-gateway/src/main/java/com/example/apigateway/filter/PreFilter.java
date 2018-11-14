package com.example.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class PreFilter extends ZuulFilter {

    /**
     * 该函数需要返回一个字符串来代表过滤器的类型,Zuul中默认定义了四种不同生命周期的过滤器类型:
     *      pre: 在请求路由之前调用
     *      routing: 在路由请求时候被调用
     *      post: 在routing和error过滤器之后被调用
     *      error: 处理请求时发生错误被调用
     * @return String
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 通过int值来定义过滤器的执行顺序,数值越小优先级越高
     * @return int
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行
     * @return boolean
     */
    @Override
    public boolean shouldFilter() {
        return false;
    }

    /**
     * 过滤器的具体逻辑
     * @return Object
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        log.info("send {} requet to {}", request.getMethod(), request.getRequestURL().toString());
        String accessToken = request.getParameter("accessToken");
        if(accessToken == null){
            log.warn("accessToken is empty");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            return null;
        }
        log.info("accessToken is ok");
        return null;
    }

}
