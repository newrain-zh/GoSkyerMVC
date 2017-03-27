package org.goskyer.helper;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.goskyer.annotation.Request;
import org.goskyer.bean.Handler;
import org.goskyer.bean.RequestBean;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zzqno on 2017-3-22.
 */
public class ControllerHelper {

    private static final Map<RequestBean, Handler> ACTION_MAP = new HashMap<>();

    /**
     * 从Action注解提取URL 初始化Request与Handler的关系
     */
    static {
        //获取所有controller类
        Set<Class<?>> controllerClasses = ClassHelper.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(controllerClasses)) {
            //遍历这些controller
            for (Class<?> controllerClass : controllerClasses) {
                //获取所有方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtils.isNotEmpty(methods)) {
                    //遍历这些方法
                    for (Method method : methods) {
                        //判断这些方法是否有注解
                        if (method.isAnnotationPresent(Request.class)) {
                            //从Action 获取URL映射规则
                            Request action = method.getAnnotation(Request.class);
                            String mapping = action.value();
                            String[] array = mapping.split(":");
                            if (ArrayUtils.isNotEmpty(array) && array.length == 2) {
                                //获取请求方法与请求路径
                                String requestMethod = array[0];
                                String requestPath = array[1];
                                requestPath = StringUtils.replaceAll(requestPath, "\\{\\w+\\}", "(\\\\w+)");
                                RequestBean request = new RequestBean(requestMethod, requestPath);
                                Handler handler = new Handler(controllerClass, method);
                                ACTION_MAP.put(request, handler);
                            }

                        }
                    }
                }

            }
        }
        System.out.println("actionMap.size is :" + ACTION_MAP.size());
    }


    /**
     * 获取handler
     *
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        RequestBean request = new RequestBean(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }

    public static Map<RequestBean, Handler> getActionMap() {
        return ACTION_MAP;
    }


}
