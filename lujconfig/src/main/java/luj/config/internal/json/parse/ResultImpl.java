package luj.config.internal.json.parse;

import java.util.List;

final class ResultImpl implements LineJsonParser.Result {

  ResultImpl(Object configInstance, List<LineJsonParser.LinkableField> linkableList) {
    _configInstance = configInstance;
    _linkableList = linkableList;
  }

  @Override
  public Object getConfigInstance() {
    return _configInstance;
  }

  @Override
  public List<LineJsonParser.LinkableField> getLinkableList() {
    return _linkableList;
  }

  private final Object _configInstance;

  private final List<LineJsonParser.LinkableField> _linkableList;
}
