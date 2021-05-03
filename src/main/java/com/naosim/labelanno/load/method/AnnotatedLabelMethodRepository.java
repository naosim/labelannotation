package com.naosim.labelanno.load.method;

import com.naosim.labelanno.load.util.ClassLoaderService;

import java.util.ArrayList;
import java.util.List;

public class AnnotatedLabelMethodRepository {
    final String rootDirForClasses;

    public AnnotatedLabelMethodRepository(String rootDirForClasses) {
        this.rootDirForClasses = rootDirForClasses;
    }

    private List<Class> classes = null;
    private List<Class> getClasses() {
        if(this.classes == null) {
            this.classes = ClassLoaderService.findAllClasses(rootDirForClasses);
        }
        return this.classes;
    }

    private List<AnnotatedLabelMethod> annotatedLabelMethods = null;
    public List<AnnotatedLabelMethod> findAll() {
        if(this.annotatedLabelMethods == null) {
            this.annotatedLabelMethods = new ArrayList<>();
            this.getClasses()
                    .stream()
                    .map(AnnotatedLabelMethod::create)
                    .forEach(this.annotatedLabelMethods::addAll);
        }
        return this.annotatedLabelMethods;
    }

}
