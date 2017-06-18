package org.goskyer.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzqno on 2017-3-24.
 * web请求工具类
 */
public final class WebUtil {

    private static final Logger log = LoggerFactory.getLogger(WebUtil.class);

    /**
     * 从Request中获取所有参数（当参数名重复时，用后者覆盖前者）
     *
     * @param request
     * @return
     */
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

    /**
     * 重定向
     *
     * @param path
     * @param request
     * @param response
     */
    public static void redirectRequest(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 转发
     *
     * @param path
     * @param request
     * @param response
     */
    public static void forwardRequest(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(path).forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 输出响应 JSON格式
     * 使用try-with-resources 处理错误
     * @param response
     * @param model
     * @throws IOException
     */
    public static void outWriterReponse(HttpServletResponse response, Object model) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            // 向响应中写入数据
            writer.write(JSON.toJSONString(model)); // 转为 JSON 字符串
            writer.flush();
            // PrintWriter 继承与 Writer  Writer 实现了AutoCloseable 可自动释放资源
            //writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 错误响应
     *
     * @param code
     * @param message
     * @param response
     */
    public static void sendError(int code, String message, HttpServletResponse response) {
        try {
            response.sendError(code, message);
        } catch (Exception e) {
            log.error("发送错误代码出错！", e);
            throw new RuntimeException(e);
        }
    }


}
