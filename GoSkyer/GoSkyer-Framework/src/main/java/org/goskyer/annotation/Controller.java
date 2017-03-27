package org.goskyer.annotation;

import java.lang.annotation.*;

/**
 * Created by zzqno on 2017-3-21.
 * controller注解
 */


@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}
