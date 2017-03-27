package org.goskyer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zzqno on 2017-3-21.
 * 反射工具类
 */
public final class ReflectionUtil {

    private static final Logger log = LoggerFactory.getLogger(ReflectionUtil.class);


    /**
     * 创建实例
     *
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls) {
        Object instance = null;
        try {
            instance = cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     *
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result = null;
        //对所有属性设置访问权限  当类中的成员变量为private时 必须设置此项
        method.setAccessible(true);
        try {
           Class<?> type[] = method.getParameterTypes();
           result = method.invoke(obj, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("invokeMethod failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * 设置成员变量的值
     *
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            log.error("set  field failure", e);
            throw new RuntimeException(e);
        }
    }

}
