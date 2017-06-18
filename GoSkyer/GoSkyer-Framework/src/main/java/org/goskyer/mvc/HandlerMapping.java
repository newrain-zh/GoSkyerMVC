package org.goskyer.mvc;

import org.goskyer.bean.Handler;

/**
 * Created by zzqno on 2017-6-18.
 */
public interface HandlerMapping {

    /**
     * 获取 Handler
     *
     * @param currentRequestMethod 当前请求方法
     * @param currentRequestPath   当前请求路径
     * @return Handler
     */
    Handler getHandler(String currentRequestMethod, String currentRequestPath);
}
