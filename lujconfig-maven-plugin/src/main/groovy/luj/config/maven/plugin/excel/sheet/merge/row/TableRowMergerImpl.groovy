package luj.config.maven.plugin.excel.sheet.merge.row

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class TableRowMergerImpl implements TableRowMerger {

  @Override
  List merge() {
    List result = []

    List<Row> lastGroup = [_rowIter.next()]
    while (_rowIter.hasNext()) {
      List<Row> nextGroup = []
      collectGroup(lastGroup, nextGroup)

      result.add(lastGroup)
      lastGroup = nextGroup
    }
    if (lastGroup) {
      result.add(lastGroup)
    }

    int colCount = result
        .collect { countColumn(it) }
        .max()

    return result
        .collect { mergeGroup(it, colCount) }
  }

  private void collectGroup(List<Row> lastGroup, List<Row> nextGroup) {
    while (_rowIter.hasNext()) {
      Row row = _rowIter.next()
      Object firstCol = row.getColumn(0)

      if (!isEmpty(firstCol)) {
        nextGroup.add(row)
        return
      }
      lastGroup.add(row)
    }
  }

  private int countColumn(List<Row> group) {
    return group.stream()
        .mapToInt { it.countColumn() }
        .max()
        .orElse(0)
  }

  private List mergeGroup(List<Row> group, int columnCount) {
    return (0..<columnCount)
        .collect { collectColumn(group, it) }
  }

  private List collectColumn(List<Row> rowList, int col) {
    return rowList
        .collect { it.getColumn(col) }
        .findAll { !isEmpty(it) }
  }

  private boolean isEmpty(Object val) {
    if (val instanceof String) {
      return val.trim().isEmpty()
    }
    return val == null
  }

  interface Row {

    Object getColumn(int index)

    int countColumn()
  }

  private Iterator<Row> _rowIter
}
