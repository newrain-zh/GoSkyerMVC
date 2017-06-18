package org.goskyer.mvc;

import org.goskyer.bean.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zzqno on 2017-6-14.
 */
public interface HandlerInvoker {

    void invokeHandler(HttpServletRequest request, HttpServletResponse response, Handler handler) throws Exception;
}
