package com.naosim.labelanno.load.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AnnotationValueGetter {
    /**
     * アノテーションから値を取得する
     *  本来ならばclass.getAnnotation(Hoge.class)等で実態が取れるべきだが
     *  なぜかnullが帰ってくるため、getAnnotations()を利用して実装する。
     *  ただその場合はAnnotationがProxyでラップされているため
     *  このメソッドが必要になる
     *
     * @param proxiedAnnotation
     * @param annotationClass value()のメソッドを持つクラス
     * @return
     */
    public static String getValue(Annotation proxiedAnnotation, Class<?> annotationClass) {
        try {
            return invokeMethod(proxiedAnnotation, annotationClass.getMethod("value"), String.class);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T invokeMethod(Object targetObject, Method method, Class<T> returnClass) {
        try {
            if (!Proxy.class.isInstance(targetObject)) {
                return returnClass.cast(method.invoke(targetObject));
            }

            // proxyでラップされている場合
            Proxy p = (Proxy) targetObject;
            InvocationHandler h = Proxy.getInvocationHandler(p);

            return returnClass.cast(h.invoke(p, method, null));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
