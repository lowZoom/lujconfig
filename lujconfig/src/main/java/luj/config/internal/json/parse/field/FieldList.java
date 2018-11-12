package luj.config.internal.json.parse.field;

import com.google.common.collect.ImmutableList;

enum FieldList implements FieldValueSetter {
  SINGLETON;

  @Override
  public void fromJson(Context ctx) throws IllegalAccessException {
    ctx.getField().set(ctx.getObject(), ImmutableList.of());
  }
}
