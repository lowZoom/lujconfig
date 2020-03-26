package luj.config.ex.internal.generate.extract.error;

import java.nio.file.Path;
import org.apache.poi.ss.util.CellAddress;

public class InvalidValueException extends RuntimeException {

  public InvalidValueException(Throwable cause, Path bookPath, String sheet, CellAddress address) {
    super(cause);

    _bookPath = bookPath;
    _sheet = sheet;
    _address = address;
  }

  public Path getBookPath() {
    return _bookPath;
  }

  public String getSheet() {
    return _sheet;
  }

  public int getRow() {
    return _address.getRow() + 1;
  }

  public int getColumn() {
    return _address.getColumn() + 1;
  }

  public String getAddress() {
    return _address.toString();
  }

  private final Path _bookPath;
  private final String _sheet;

  private final CellAddress _address;
}
