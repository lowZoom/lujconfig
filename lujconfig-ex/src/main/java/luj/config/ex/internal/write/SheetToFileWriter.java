package luj.config.ex.internal.write;

import java.util.Map;
import luj.config.ex.api.out.ConfigFileWriter;
import luj.config.ex.internal.read.extract.book.ExcelDataExtractor;

public class SheetToFileWriter {

  public SheetToFileWriter(ExcelDataExtractor.DataSheet sheet, Map<Class<?>, Object> context) {
    _sheet = sheet;
    _context = context;
  }

  public void write() {
    invokeWrite();
  }

  private void invokeWrite() {
    ContextImpl ctx = new ContextImpl();
    ctx._sheet = _sheet;

    ConfigFileWriter writer = (ConfigFileWriter) _context.get(ConfigFileWriter.class);
    writer.onWrite(ctx);
  }

  private final ExcelDataExtractor.DataSheet _sheet;

  private final Map<Class<?>, Object> _context;
}
