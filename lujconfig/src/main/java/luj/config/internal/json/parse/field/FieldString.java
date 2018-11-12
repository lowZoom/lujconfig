package luj.config.internal.json.parse.field;

enum FieldString implements FieldValueSetter {
  SINGLETON;

  @Override
  public void fromJson(Context ctx) throws IllegalAccessException {
    String value = ctx.getJsonNode().asText();
    ctx.getField().set(ctx.getObject(), value);
  }
}
