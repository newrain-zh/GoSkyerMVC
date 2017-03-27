package org.goskyer.annotation;

import java.lang.annotation.*;

/**
 * Created by zzqno on 2017-3-22.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Request {
    String value();

}
