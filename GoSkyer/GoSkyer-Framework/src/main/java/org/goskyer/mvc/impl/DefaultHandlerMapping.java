package org.goskyer.mvc.impl;

import org.goskyer.annotation.Request;
import org.goskyer.bean.Handler;
import org.goskyer.bean.RequestBean;
import org.goskyer.helper.ControllerHelper;
import org.goskyer.mvc.HandlerMapping;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 默认处理器映射
 *
 * @author huangyong
 * @since 2.3
 */
public class DefaultHandlerMapping implements HandlerMapping {

    @Override
    public Handler getHandler(String currentRequestMethod, String currentRequestPath) {
        // 定义一个 Handler
        Handler handler = null;
        // 获取并遍历 Action 映射
        Map<RequestBean, Handler> actionMap = ControllerHelper.getActionMap();
        if (actionMap == null || actionMap.isEmpty()) {
            return null;
        }
        for (Map.Entry<RequestBean, Handler> actionEntry : actionMap.entrySet()) {
            // 从 Requester 中获取 Request 相关属性
            RequestBean requester = actionEntry.getKey();
            String requestMethod = requester.getRequestMethod();
            String requestPath = requester.getRequestPath(); // 正则表达式
            // 获取请求路径匹配器（使用正则表达式匹配请求路径并从中获取相应的请求参数）
            Matcher requestPathMatcher = Pattern.compile(requestPath).matcher(currentRequestPath);
            // 判断请求方法与请求路径是否同时匹配
            if (requestMethod.equalsIgnoreCase(currentRequestMethod) && requestPathMatcher.matches()) {
                // 获取 Handler 及其相关属性
                handler = actionEntry.getValue();
                // 设置请求路径匹配器
                if (handler != null) {
                    handler.setRequestPathMatcher(requestPathMatcher);
                }
                // 若成功匹配，则终止循环
                break;
            }
        }
        // 返回该 Handler
        return handler;
    }
}
