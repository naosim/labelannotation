package com.naosim.labelanno.load;

import com.naosim.labelanno.load.clazz.AnnotatedLabelClass;
import com.naosim.labelanno.load.clazz.AnnotatedLabelClassRepository;
import com.naosim.labelanno.load.method.AnnotatedLabelMethod;
import com.naosim.labelanno.load.method.AnnotatedLabelMethodRepository;
import com.naosim.labelanno.load.util.ClassLoaderService;

import java.util.ArrayList;
import java.util.List;

public class AnnotatedLabelRepository {
    final String rootDirForClasses;
    final AnnotatedLabelClassRepository classRepository;
    final AnnotatedLabelMethodRepository methodRepository;
    public AnnotatedLabelRepository(String rootDirForClasses) {
        this.rootDirForClasses = rootDirForClasses;
        this.classRepository = new AnnotatedLabelClassRepository(this.rootDirForClasses);
        this.methodRepository = new AnnotatedLabelMethodRepository(this.rootDirForClasses);
    }

    private List<Class> classes = null;
    private List<Class> getClasses() {
        if(this.classes == null) {
            this.classes = ClassLoaderService.findAllClasses(rootDirForClasses);
        }
        return this.classes;
    }

    private List<AnnotatedLabelClass> annotatedLabelClasses = null;
    public List<AnnotatedLabelClass> findAllLabelAnnotatedClass() {
        if(this.annotatedLabelClasses == null) {
            this.annotatedLabelClasses = this.classRepository.findAll();
        }
        return this.annotatedLabelClasses;
    }

    private List<AnnotatedLabelMethod> annotatedLabelMethods = null;
    public List<AnnotatedLabelMethod> findAllLabelAnnotatedMethod() {
        if(this.annotatedLabelMethods == null) {
            this.annotatedLabelMethods = this.methodRepository.findAll();
        }
        return this.annotatedLabelMethods;
    }

    private List<AnnotatedLabel> list = null;
    public List<AnnotatedLabel> findAll() {
        if(this.list == null) {
            this.list = new ArrayList<>();
            this.classRepository.findAll().forEach(v -> this.list.add(AnnotatedLabel.create(v)));
            this.methodRepository.findAll().forEach(v -> this.list.add(AnnotatedLabel.create(v)));
        }
        return this.list;
    }
}
