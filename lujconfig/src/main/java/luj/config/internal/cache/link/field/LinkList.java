package luj.config.internal.cache.link.field;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import luj.ava.reflect.generic.GenericType;

enum LinkList implements ConfigLinkBehavior {
  SINGLETON;

  @Override
  public void link(Context ctx) {
    Field field = ctx.getField();
    Class<?> linkType = GenericType.ofField(field).getTypeArg(0);

    List<Object> value = ctx.getJsonValue().asStringList().stream()
        .map(id -> ctx.findLinkInstance(linkType, id))
        .collect(Collectors.toList());

    ctx.setFieldValue(value);
  }
}
