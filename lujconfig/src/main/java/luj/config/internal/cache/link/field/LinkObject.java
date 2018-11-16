package luj.config.internal.cache.link.field;

enum LinkObject implements ConfigLinkBehavior {
  SINGLETON;

  @Override
  public void link(Context ctx) {
    Class<?> linkType = ctx.getField().getType();
    String configId = ctx.getJsonValue().asString();

    Object value = ctx.findLinkInstance(linkType, configId);
    ctx.setFieldValue(value);
  }
}
