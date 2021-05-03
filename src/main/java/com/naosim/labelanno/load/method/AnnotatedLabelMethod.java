package com.naosim.labelanno.load.method;

import com.naosim.labelanno.Label;
import com.naosim.labelanno.load.util.AnnotationValueGetter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * ラベルアノテーションのついたメソッド
 */
public class AnnotatedLabelMethod {
    public final Class clazz;
    public final Method method;
    public final String value;

    AnnotatedLabelMethod(Class clazz, Method method, Annotation labelAnnotation) {
        this.clazz = clazz;
        this.method = method;
        this.value = AnnotationValueGetter.getValue(labelAnnotation, Label.class);
    }

    public static List<AnnotatedLabelMethod> create(Class clazz) {
        List<AnnotatedLabelMethod> list = new ArrayList<>();
        Stream.of(clazz.getMethods())
                .forEach(v -> {
                    Stream.of(v).forEach(m -> {
                       Stream.of(m.getAnnotations())
                               .filter((a) -> Label.class.getName().equals(a.annotationType().getName()))
                               .forEach(a -> list.add(new AnnotatedLabelMethod(clazz, m ,a)));
                    });
                });
        return list;

    }
}
