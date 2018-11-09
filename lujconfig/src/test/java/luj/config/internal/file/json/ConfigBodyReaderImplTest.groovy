package luj.config.internal.file.json

import spock.lang.Specification

import java.util.stream.Collectors
import java.util.stream.Stream

class ConfigBodyReaderImplTest extends Specification {

  String _lineStr

  void setup() {
    // NOOP
  }

  def "Read:"() {
    given:
    _lineStr = '''\
{"header":
{}
,"body":[
{"id":101},
{"id":102}
]}

'''
    when:
    def result = read()

    then:
    result.collect(Collectors.toList()) == [
        /{"id":101}/,
        /{"id":102}/,
    ]
  }

  Stream<String> read() {
    return new ConfigBodyReaderImpl(Arrays.stream(_lineStr.split('\n'))).read()
  }
}
