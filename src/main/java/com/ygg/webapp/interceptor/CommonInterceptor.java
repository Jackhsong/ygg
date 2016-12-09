package com.ygg.webapp.interceptor;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.ygg.webapp.entity.QqbsAccountEntity;
import com.ygg.webapp.service.wechat.WeChatService;
import com.ygg.webapp.util.CommonConstant;
import com.ygg.webapp.util.CommonUtil;
import com.ygg.webapp.util.SessionUtil;
import com.ygg.webapp.util.WeixinMessageDigestUtil;
import com.ygg.webapp.util.YggWebProperties;
import com.ygg.webapp.view.AccountView;

public class CommonInterceptor extends HandlerInterceptorAdapter
{
    private static final Logger logger = Logger.getLogger(CommonInterceptor.class);
    
    private List<String> excludeUrls;
    
    private String basePath;
    
    @Resource
    private WeChatService weChatService;
    
    public List<String> getExcludeUrls()
    {
        return excludeUrls;
    }
    
    public void setExcludeUrls(List<String> excludeUrls)
    {
        this.excludeUrls = excludeUrls;
    }
    
    /**
     * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕
     * 再执行被拦截的Controller 然后进入拦截器链, 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception
    {
    
            String fxvalue = (request.getParameter(CommonConstant.OrderSoucrChannelKey) == null ? "" : request.getParameter(CommonConstant.OrderSoucrChannelKey).trim());
            if (!fxvalue.equals(""))
            {
                response.addCookie(CommonUtil.addCookie(CommonConstant.OrderSoucrChannelKey, fxvalue, 60 * 60 * 24 * 15));
            }
            
            // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            // log.info("==============执行顺序: 1、preHandle================");
            String requestUri = request.getRequestURI();
            String contextPath = request.getContextPath();
            String url = requestUri.substring(contextPath.length());
            
            // String completeUrl = request.getScheme() + "://" + request.getServerName() + ":" +
            // request.getServerPort() + requestUri + "/";
            int port = request.getServerPort();
            basePath = null;
            return true;
           
    }
    
    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
        throws Exception
    {
        
        request.setAttribute("basePath", basePath);
        super.postHandle(request, response, handler, modelAndView);
        // log.info("==============执行顺序: 2、postHandle================");
        
    }
    
    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception
    {
        super.afterCompletion(request, response, handler, ex);
        // log.info("==============执行顺序: 3、afterCompletion================");
        
    }
    
}
