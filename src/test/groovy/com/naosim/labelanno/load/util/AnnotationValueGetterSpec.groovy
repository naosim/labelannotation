package com.naosim.labelanno.load.util

import com.naosim.labelanno.Label
import com.naosim.sample.Other
import com.naosim.sample.Sample
import spock.lang.Specification

class AnnotationValueGetterSpec extends Specification {
    def "GetValue"() {
        when:
        def act = AnnotationValueGetter.getValue(Sample.class.getAnnotations()[0], Label.class)


        then:
        act != null
    }

    def "GetValue_クラスが違っていてもvalueメソッドがあればok"() {
        when:
        def act = AnnotationValueGetter.getValue(Sample.class.getAnnotations()[0], Other.class)

        then:
        act != null
    }

    def "GetValue_クラスが違っていたらエラー"() {
        when:
        def act = AnnotationValueGetter.getValue(Sample.class.getAnnotations()[0], String.class)

        then:
        thrown(RuntimeException)
    }
}
