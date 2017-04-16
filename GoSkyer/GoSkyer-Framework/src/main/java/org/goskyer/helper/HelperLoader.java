package org.goskyer.helper;

import org.goskyer.util.ClassUtil;

/**
 * Created by zzqno on 2017-3-22.
 * 加载助手类
 */
public final class HelperLoader {

    /**
     * 加载相应的Helper类
     */
    public static void init() {
        Class<?> classList[] = {ClassHelper.class, BeanHelper.class,AopHelper.class, IocHelper.class, ControllerHelper.class};
        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName(),false);
        }
    }

}
