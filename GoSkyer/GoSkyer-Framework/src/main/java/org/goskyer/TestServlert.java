package org.goskyer;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.goskyer.bean.Data;
import org.goskyer.bean.Handler;
import org.goskyer.bean.Param;
import org.goskyer.bean.View;
import org.goskyer.helper.BeanHelper;
import org.goskyer.helper.ConfigHelper;
import org.goskyer.helper.ControllerHelper;
import org.goskyer.helper.HelperLoader;
import org.goskyer.util.ReflectionUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Created by zzqno on 2017-3-24.
 */
@WebServlet(urlPatterns = "/", loadOnStartup = 0)
public class TestServlert extends HttpServlet {

    @Override
    public void init() throws ServletException {
        //初始化相关的Help类
        HelperLoader.init();
        //servletContext 用于注册servlet
        ServletContext servletContext = getServletConfig().getServletContext();
        //注册处理JSP Servlet
        ServletRegistration jspServlert = servletContext.getServletRegistration("jsp");
        jspServlert.addMapping(ConfigHelper.getAppJspPath() + "*");
        //注册处理静态资源的默认的servlet
        ServletRegistration defaultServllet = servletContext.getServletRegistration("default");
        defaultServllet.addMapping(ConfigHelper.getAppAssetPath());
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //获取当前请求动作与url
        String currentRequestMethod = req.getMethod().toLowerCase();
        String currentRequestUrl = req.getRequestURI();
        System.out.println("loding url:" + currentRequestUrl);
        //获取处理器
        Handler handler = ControllerHelper.getHandler(currentRequestMethod, currentRequestUrl);
        if (handler != null) {
            //获取Controller类及其Bean实例
            Class<?> controllerClass = handler.getControllerClass();
            Object cotrollerBean = BeanHelper.getBean(controllerClass);
            //获取请求参数与其值
            Map<String, Object> paramsMap = new HashedMap();
            List<Object> methodParams = new ArrayList();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String value = req.getParameter(paramName);
                paramsMap.put(paramName, value);
                methodParams.add(value);
            }
            //获取流式数据
         /*   String body = CodecUtil.decodeURL(StreamUtil.get(req.getInputStream()));
            System.out.println("body: "+ body);
            if(StringUtils.isNotEmpty(body)){
                String[] params = StringUtils.split("")
            }*/
            Param param = new Param(paramsMap);
            //调用action方法 获取方法结果
            Method method = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(cotrollerBean, method, methodParams.toArray());
            //如果方法返回值是直接返回Data类型数据
            if (result instanceof Data) {
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null) {
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    PrintWriter writer = res.getWriter();
                    writer.write(JSON.toJSONString(model));
                    writer.flush();
                    writer.close();
                }
                //若返回的view对象 进行转发
            } else if (result instanceof View) {
                View view = (View) result;
                String path = view.getPath();
                if (StringUtils.isNotEmpty(path)) {
                    if (path.startsWith("/")) {
                        res.sendRedirect(req.getContextPath() + path);
                    } else {
                        if(!path.endsWith(".jsp")){
                            path = path + ".jsp";
                        }
                        Map<String, Object> model = view.getModel();
                        for (Map.Entry<String, Object> entry : model.entrySet()) {
                            req.setAttribute(entry.getKey(), entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, res);
                    }
                }
            }
        }
    }


}
