package luj.config.internal.cache.link;

import java.lang.reflect.Field;
import luj.config.internal.cache.link.field.ConfigLinkBehavior;
import luj.config.internal.cache.link.field.ConfigLinkBehaviorFactory;
import luj.config.internal.cache.value.ConfigValueMapLoader;

final class LinkableFieldImpl implements ConfigInstanceLinkerImpl.LinkableField {

  LinkableFieldImpl(ConfigValueMapLoader.LinkableField parseField) {
    _parseField = parseField;
  }

  @Override
  public void link() {
    Field field = _parseField.getField();
    Class<?> fieldType = field.getType();

    ConfigLinkBehaviorFactory linkFactory = ConfigLinkBehaviorFactory.getInstance();
    ConfigLinkBehavior linkBehavior = linkFactory.create(fieldType);

    linkBehavior.link(ConfigLinkBehavior.ContextFactory.create(_parseField));
  }

  private final ConfigValueMapLoader.LinkableField _parseField;
}
