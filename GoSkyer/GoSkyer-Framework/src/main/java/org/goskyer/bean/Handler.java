package org.goskyer.bean;

import java.lang.reflect.Method;

/**
 * Created by zzqno on 2017-3-22.
 * 封装controller类与方法信息
 */
public class Handler {


    private Class<?> controllerClass;

    //action 方法
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
