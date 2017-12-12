abstract class Variable {
  private String name;
  private Register register;

  public Variable(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setRegister(Register reg) {
    register = reg;
  }

  public Register getRegister() {
    return register;
  }
}
