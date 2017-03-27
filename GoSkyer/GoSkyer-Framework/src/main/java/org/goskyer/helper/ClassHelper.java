package org.goskyer.helper;


import org.goskyer.annotation.Controller;
import org.goskyer.annotation.Service;
import org.goskyer.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zzqno on 2017-3-21.
 * class助手 用于获取所有被注解的类
 */
public final class ClassHelper {

    /**
     * 定义集合类（用于存放加载的类）
     */
    private static Set<Class<?>> CLASS_SET = null;

    static {
        String basePackename = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackename);
    }

    /**
     * 获取应用名包下的所有类
     *
     * @return
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 获取应用包下所有service类
     * @return
     */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if(cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取controller类
     * @return
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if(cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取所有class
     * @return
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> classSet = new HashSet<>();
        classSet.addAll(getServiceClassSet());
        classSet.addAll(getControllerClassSet());
        return classSet;
    }

}
