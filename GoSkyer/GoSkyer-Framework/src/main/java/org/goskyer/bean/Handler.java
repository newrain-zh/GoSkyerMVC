package org.goskyer.bean;

import java.lang.reflect.Method;
import java.util.regex.Matcher;

/**
 * Created by zzqno on 2017-3-22.
 * 封装controller类与方法信息
 */
public class Handler {


    private Class<?> controllerClass;

    //controller 方法
    private Method controllerMethod;

    //正则表达式
    private Matcher requestPathMatcher;


    public Handler(Class<?> controllerClass, Method controllerMethod) {
        this.controllerClass = controllerClass;
        this.controllerMethod = controllerMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getControllerMethod() {
        return controllerMethod;
    }


    public Matcher getRequestPathMatcher() {
        return requestPathMatcher;
    }

    public void setRequestPathMatcher(Matcher requestPathMatcher) {
        this.requestPathMatcher = requestPathMatcher;
    }
}
