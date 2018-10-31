package luj.config.internal.meta;

import static com.google.common.base.MoreObjects.firstNonNull;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.stream.Collectors;
import luj.ava.spring.Internal;
import luj.config.internal.meta.spring.ConfigMetaHolder;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ConfigMetaCollectorImpl implements ConfigMetaCollector {

  @Override
  public List<ConfigMeta> collect() {
    return firstNonNull(_metaHolderList, ImmutableList.of()).stream()
        .map(ConfigMetaHolder.class::cast)
        .map(h -> new ConfigMetaFactory(h).create())
        .collect(Collectors.toList());
  }

  @Autowired(required = false)
  private List<ConfigMetaHolder<?>> _metaHolderList;
}
