package luj.config.maven.plugin.excel.sheet.merge.row

import groovy.transform.PackageScope

@PackageScope
class TableRowMergerImpl implements TableRowMerger {

  TableRowMergerImpl(Iterator<Row> rowIter) {
    _rowIter = rowIter
  }

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

  /**
   * @param columnCount 字段总数量
   */
  private List mergeGroup(List<Row> group, int columnCount) {
    return (0..<columnCount)
        .collect { collectColumn(group, it) }
  }

  /**
   * 收集同一列中的非空值（即去除列中的空值）
   *
   * @param rowList 同一数据项包含的所有行
   */
  private List collectColumn(List<Row> rowList, int col) {
    return rowList
        .collect { it.getColumn(col) }
        .findAll { !isEmpty(it) }
  }

  /**
   * 判断是否为空（即excel里没填东西）
   */
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

  private final Iterator<Row> _rowIter
}
