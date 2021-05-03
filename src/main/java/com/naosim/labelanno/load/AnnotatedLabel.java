package com.naosim.labelanno.load;

import com.naosim.labelanno.load.clazz.AnnotatedLabelClass;
import com.naosim.labelanno.load.method.AnnotatedLabelMethod;

import java.lang.reflect.Method;
import java.util.Optional;

public class AnnotatedLabel {
    public final Class clazz;
    public final Optional<Method> method;
    public final String value;
    public final boolean isClass;
    public final boolean isMethod;

    public AnnotatedLabel(Class clazz, Optional<Method> method, String value) {
        this.clazz = clazz;
        this.method = method;
        this.value = value;
        this.isMethod = method.isPresent();
        this.isClass = !method.isPresent();
    }

    public static AnnotatedLabel create(AnnotatedLabelClass clazz) {
        return new AnnotatedLabel(clazz.clazz, Optional.empty(), clazz.value);
    }
    public static AnnotatedLabel create(AnnotatedLabelMethod method) {
        return new AnnotatedLabel(method.clazz, Optional.of(method.method), method.value);
    }

}
