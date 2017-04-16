package org.goskyer.annotation;

import java.lang.annotation.*;

/**
 * Created by zzqno on 2017-3-27.
 * 切面注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 包名
     */
    String pkg() default "";

    /**
     * 类名
     */
    String cls() default "";

    /**
     * 注解
     */
    Class<? extends Annotation> annotation() default Aspect.class;


}
