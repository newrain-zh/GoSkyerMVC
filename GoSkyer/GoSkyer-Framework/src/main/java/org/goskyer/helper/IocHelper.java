package org.goskyer.helper;


import org.apache.commons.lang3.ArrayUtils;
import org.goskyer.annotation.Inject;
import org.goskyer.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by zzqno on 2017-3-22.
 * IOC 助手类
 */
public class IocHelper {

    /**
     * IOC 容器初始化
     */
    static {
        //获取容器
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        //遍历map
        if (!beanMap.isEmpty()) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                //从BeanMap中获取Bean类与Bean实例
                Class<?> cls = beanEntry.getKey();
                Object obj = beanEntry.getValue();
                //获取Bean类定义的所有成员变量（bean field）
                Field[] fields = cls.getDeclaredFields();
                if (ArrayUtils.isNotEmpty(fields)) {
                    //遍历Bean field
                    for (Field field : fields) {
                        //判断当前 Bean Field 是否带有Inject注解
                        if (field.isAnnotationPresent(Inject.class)) {
                            //从beanmap中获取Bean Field对应的实例
                            Class<?> beanFieldClass = field.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                //通过反射初始化BeanField实例
                                ReflectionUtil.setField(beanFieldInstance, field, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
