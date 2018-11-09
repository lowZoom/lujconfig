package luj.config.internal.file.json;

import java.util.stream.Stream;

public interface ConfigBodyReader {

  interface Factory {

    static ConfigBodyReader create(Stream<String> lineStream) {
      return new ConfigBodyReaderImpl(lineStream);
    }
  }

  Stream<String> read();
}
