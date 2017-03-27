package org.goskyer.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzqno on 2017-3-24.
 */
public final class WebUtil {

    // 从Request中获取所有参数（当参数名重复时，用后者覆盖前者）
    public static Map<String, Object> getRequestParamMap(HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            paramMap.put(paramName, paramValue);
        }
        return paramMap;
    }
}
