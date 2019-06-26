package com.ludata.luDataTest.resolver;

import com.ludata.luDataTest.customComment.ListParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * List集合参数解析器 <br/>
 * @author zengyuanjun
 *
 */
@Component
public class ListArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (null != parameter.getParameterAnnotation(ListParam.class)
                && List.class.equals(parameter.getParameterType())) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String[] parameterValues = null;
        MutablePropertyValues mutablePropertyValues = null;
        Class<?> elementClass = getElementTypeFromAnnotation(parameter);
        List<MutablePropertyValues> mpvList = new ArrayList<MutablePropertyValues>();
        Field[] fields = elementClass.getDeclaredFields();
        for (Field field : fields) {
            parameterValues = webRequest.getParameterValues(field.getName());
            if (null == parameterValues) {
                continue;
            }
            for (int i = 0; i < parameterValues.length; i++) {
                if (mpvList.size() <= i) {
                    mutablePropertyValues = new MutablePropertyValues();
                    mutablePropertyValues.add(field.getName(), parameterValues[i]);
                    mpvList.add(mutablePropertyValues);
                } else {
                    mutablePropertyValues = mpvList.get(i);
                    mutablePropertyValues.add(field.getName(), parameterValues[i]);
                }
            }
        }

        String name = ClassUtils.getShortNameAsProperty(elementClass);
        Object attribute = null;
        WebDataBinder binder = null;
        ServletRequestDataBinder servletBinder = null;
        Object element = null;
        List<Object> actualParameter = new ArrayList<Object>(mpvList.size());
        for (int i = 0; i < mpvList.size(); i++) {
            attribute = BeanUtils.instantiateClass(elementClass);
            binder = binderFactory.createBinder(webRequest, attribute, name);
            servletBinder = (ServletRequestDataBinder) binder;
            servletBinder.bind(mpvList.get(i));
            element = binder.getTarget();
            //Object newObj=binder.convertIfNecessary(element, elementClass, parameter);
            actualParameter.add(element);
        }

        return actualParameter;
    }

    private Class<?> getElementTypeFromAnnotation(MethodParameter parameter) {
        ListParam parameterAnnotation = parameter.getParameterAnnotation(ListParam.class);
        return parameterAnnotation.value();
    }

}