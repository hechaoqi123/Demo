package com.example.validparam.scope;

/**
 * 自定义Bean Scope
 *
 * @author hcq
 * @date 2020/5/14 17:40
 */

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope("ThreadScope")
public @interface ThreadScope {

    /**
     * @see Scope#proxyMode()
     */
    ScopedProxyMode proxyMode() default ScopedProxyMode.TARGET_CLASS;
}
