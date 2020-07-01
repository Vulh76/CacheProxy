package ru.stb.proxy.utils;

import java.util.Objects;

public class CacheKey {
    private final String methodName;
    private final Object[] args;

    public CacheKey(String methodName, Object[] args) {
        this.methodName = methodName;
        this.args = args;
    }

    @Override
    public int hashCode() {
        int hash = methodName.hashCode();
        for (Object arg : args) {
            hash |= arg.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CacheKey that = (CacheKey) obj;
        if(!this.methodName.equals(that.methodName)) return false;
        if(this.args.length != that.args.length) return false;
        for (int i = 0; i < args.length; i++) {
            if(!this.args[i].equals(that.args[i])) return false;
        }

        return true;
    }
}
