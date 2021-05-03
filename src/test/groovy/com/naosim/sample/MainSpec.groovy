package com.naosim.sample

import com.naosim.labelanno.load.AnnotatedLabelRepository
import spock.lang.Specification

class MainSpec
        extends Specification
{
    def "main"() {
        setup:
        new AnnotatedLabelRepository("./build/classes").findAll().forEach {
            System.out.println(it.clazz);
            it.method.ifPresent {System.out.println(it) };
            System.out.println(it.value);
        };

        expect:
        1 == 1
    }
}
