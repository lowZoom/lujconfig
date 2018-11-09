package luj.config.internal.file.json;

import java.util.stream.Stream;

final class ConfigBodyReaderImpl implements ConfigBodyReader {

  ConfigBodyReaderImpl(Stream<String> lineStream) {
    _lineStream = lineStream;
  }

  @Override
  public Stream<String> read() {
    return _lineStream.skip(3)
        .filter(s -> s.startsWith("{\""))
        .map(this::stripTrailingComma);
  }

  private String stripTrailingComma(String line) {
    return line.endsWith("},") ? line.substring(0, line.length() - 1) : line;
  }

  private final Stream<String> _lineStream;
}
