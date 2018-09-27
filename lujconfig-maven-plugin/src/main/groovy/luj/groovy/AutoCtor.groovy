package luj.groovy

import groovy.transform.AnnotationCollector
import groovy.transform.TupleConstructor

@TupleConstructor(includeFields = true, includeProperties = false, force = true)
@AnnotationCollector
class AutoCtor {
  // NOOP
}
