package com.naosim.labelanno.load.clazz;

import com.naosim.labelanno.load.util.ClassLoaderService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnnotatedLabelClassRepository {
    final String rootDirForClasses;

    public AnnotatedLabelClassRepository(String rootDirForClasses) {
        this.rootDirForClasses = rootDirForClasses;
    }

    private List<Class> classes = null;
    private List<Class> getClasses() {
        if(this.classes == null) {
            this.classes = ClassLoaderService.findAllClasses(rootDirForClasses);
        }
        return this.classes;
    }

    private List<AnnotatedLabelClass> annotatedLabelClasses = null;
    public List<AnnotatedLabelClass> findAll() {
        if(this.annotatedLabelClasses == null) {
            this.annotatedLabelClasses = this.getClasses().stream().map(AnnotatedLabelClass::create)
                    .filter(Optional::isPresent)
                    .map(Optional::get).collect(Collectors.toList());
        }
        return this.annotatedLabelClasses;
    }
}
