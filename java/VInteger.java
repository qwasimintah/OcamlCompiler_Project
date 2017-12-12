public class VInteger extends Variable {
  private Integer value;

  public VInteger(String name, Integer value) {
    super(name);
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }
}
