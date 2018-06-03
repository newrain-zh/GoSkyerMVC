package org.goskyer;

import org.goskyer.bean.Handler;
import org.goskyer.bean.RequestBean;
import org.goskyer.helper.ControllerHelper;
import org.goskyer.helper.HelperLoader;
import org.goskyer.mvc.HandlerInvoker;
import org.goskyer.mvc.HandlerMapping;
import org.goskyer.mvc.impl.DefaultHandlerInvoker;
import org.goskyer.mvc.impl.DefaultHandlerMapping;
import org.goskyer.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * Created by zzqno on 2017-3-24.
 */
@WebServlet(urlPatterns = "/", loadOnStartup = 0)
public class TestServlert extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(TestServlert.class);

    private HandlerInvoker handlerInvoker = new DefaultHandlerInvoker();
    private HandlerMapping handlerMapping = new DefaultHandlerMapping();

    @Override
    public void init() throws ServletException {
        //初始化相关的Help类
        HelperLoader.init();
        //servletContext 用于注册servlet
        ServletContext servletContext = getServletConfig().getServletContext();
        Map<RequestBean, Handler> actionMap = ControllerHelper.getActionMap();

        //注册处理JSP Servlet
      /*  ServletRegistration jspServlert = servletContext.getServletRegistration("jsp");
        jspServlert.addMapping(ConfigHelper.getAppJspPath() + "*");
      */
        //注册处理静态资源的默认的servlet
     /*   ServletRegistration defaultServllet = servletContext.getServletRegistration("default");
        defaultServllet.addMapping(ConfigHelper.getAppAssetPath());*/
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //获取当前请求动作与url
        String currentRequestMethod = req.getMethod().toLowerCase();
        String currentRequestUrl = req.getRequestURI();
        log.debug("[goSkyerMVC] {}:{}", currentRequestMethod, currentRequestUrl);
        //获取处理器
        //Handler handler = ControllerHelper.getHandler(currentRequestMethod, currentRequestUrl);
        Handler handler = handlerMapping.getHandler(currentRequestMethod, currentRequestUrl);
        //发送404状态码
        if (handler == null) {
            WebUtil.sendError(HttpServletResponse.SC_NOT_FOUND, "", res);
            return;
        }
        try {
            handlerInvoker.invokeHandler(req, res, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
