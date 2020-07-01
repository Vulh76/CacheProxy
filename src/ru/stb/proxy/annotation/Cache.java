package ru.stb.proxy.annotation;

import ru.stb.proxy.utils.CacheType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    CacheType cacheType() default CacheType.FILE;
    String fileNamePrefix() default "data";
    int listLimit() default 1000;
    boolean zip() default false;
    Class<?>[] identityBy() default {};
}
