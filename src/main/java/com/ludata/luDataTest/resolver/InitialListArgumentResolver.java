package com.ludata.luDataTest.resolver;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * 初始化将自定义的ListArgumentResolver注入到RequestMappingHandlerAdapter适配器中
 * @author zengyuanjun
 *
 */
@Component
public class InitialListArgumentResolver implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RequestMappingHandlerAdapter handlerAdapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);
        List<HandlerMethodArgumentResolver> argumentResolvers = handlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> resolvers = new LinkedList<HandlerMethodArgumentResolver>();
        resolvers.add(new ListArgumentResolver());
        resolvers.addAll(argumentResolvers);
        handlerAdapter.setArgumentResolvers(resolvers);
    }
}