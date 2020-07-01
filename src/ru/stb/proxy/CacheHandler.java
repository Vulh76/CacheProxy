package ru.stb.proxy;

import ru.stb.proxy.annotation.Cache;
import ru.stb.proxy.utils.CacheKey;
import ru.stb.proxy.utils.CacheMap;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CacheHandler implements InvocationHandler {
    private final Object original;
    private final String cacheDir;
    private final CacheMap<CacheKey, Object> cacheData;

    /**
     * Конструктор принимает общие параметры кэширования
     *
     * @param original обект, для которого создается прокси
     * @param cacheDir каталог, в тором будут создаваться файлы кэша, если выбрано сохраниений кэша в файл
     * @param cacheSize максимальный размер кэша
     */
    public CacheHandler(Object original, String cacheDir, int cacheSize) {
        this.original = original;
        this.cacheDir = cacheDir;
        this.cacheData = new CacheMap<>(cacheSize);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.isAnnotationPresent(Cache.class)) {
            Cache cacheAnnotation = method.getAnnotation(Cache.class);
            CacheKey cacheKey = new CacheKey(method.getName(), args);
            Object cacheValue = cacheData.get(cacheKey);
            if(cacheValue != null) return cacheValue;

            Object result = method.invoke(original, args);
            cacheData.put(cacheKey, result);
            return result;
        }
        return method.invoke(original, args);
    }
}
