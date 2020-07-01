package ru.stb.proxy;

import ru.stb.service.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class CacheProxy {
    private final String cacheDir;

    public CacheProxy(String cacheDid) {
        this.cacheDir = cacheDid;
    }

    public Service cache(Service service) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Class<?> proxyClass = Proxy.getProxyClass(
                Service.class.getClassLoader(),
                Service.class
        );

        return (Service) proxyClass.getConstructor(InvocationHandler.class).
                newInstance(new CacheHandler(service, cacheDir, 1000));
    }
}
