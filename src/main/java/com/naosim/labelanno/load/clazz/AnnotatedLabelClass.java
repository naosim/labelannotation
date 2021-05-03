package com.naosim.labelanno.load.clazz;

import com.naosim.labelanno.Label;
import com.naosim.labelanno.load.util.AnnotationValueGetter;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * ラベルアノテーションのついたクラス
 */
public class AnnotatedLabelClass {
    public final Class clazz;
    public final String value;

    AnnotatedLabelClass(Class clazz, Annotation labelAnnotation) {
        this.clazz = clazz;
        this.value = AnnotationValueGetter.getValue(labelAnnotation, Label.class);
    }

    public static Optional<AnnotatedLabelClass> create(Class clazz) {
        return Stream.of(clazz.getAnnotations())
                .filter((Annotation v) -> Label.class.getName().equals(v.annotationType().getName()))
                .map(v -> new AnnotatedLabelClass(clazz, v))
                .findFirst();
    }
}
