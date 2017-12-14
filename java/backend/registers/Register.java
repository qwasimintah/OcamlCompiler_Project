package registers;

public class Register {
  private Integer index;

  public Register(Integer index) {
    this.index = index;
  }

  public Integer getIndex() {
    return index;
  }

  public String getName() {
    return "r" + index.toString();
  }
}
