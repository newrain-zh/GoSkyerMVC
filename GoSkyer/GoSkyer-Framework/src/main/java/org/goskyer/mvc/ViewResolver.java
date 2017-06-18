package org.goskyer.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zzqno on 2017-6-14.
 */
public interface ViewResolver {

    void resolveView(HttpServletRequest request, HttpServletResponse response, Object actionMethodResult);
}
