package com.shihang.usermerge.usermergeconsmerinsertold.config;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author jiangzhuangnai
 * @date 2021/2/5
 * @since 1.0.0
 **/
public class TypeReference<T> {

    private final Type type;

    protected TypeReference() {
        Type superClass = getClass().getGenericSuperclass();
        this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }

    public final static Type LIST_STRING = new TypeReference<List<String>>() {
    }.getType();
}
