package org.goskyer.helper;

import org.goskyer.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zzqno on 2017-3-21.
 * Bean助手类
 * TODO 此处有优化和商榷的可能
 */
public final class BeanHelper {

    /**
     * 定义Bean映射（用于存放Bean类与Bean实例的映射关系）
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    /**
     * 加载所有class 并创建对象 放入BEAN_MAP来保存类和对象的映射关系
     * TODO 这里是粗粒度的实现 在Spring中 Bean的生命周期不一样 默认为单例 这里就会产生线程安全问题
     */
    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> cls : beanClassSet) {
            Object object = ReflectionUtil.newInstance(cls);
            BEAN_MAP.put(cls, object);
        }
    }

    /**
     * 获取bean容器
     *
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 添加Bean
     * @param clazz
     * @param object
     */
    public static void setBean(Class<?> clazz, Object object) {
        BEAN_MAP.put(clazz, object);
    }

    /**
     * 获取Bean的实例对象
     *
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class:" + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }


}
