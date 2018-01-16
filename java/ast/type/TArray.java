package ast.type;

public class TArray extends Type {
  public Type t;
  public TArray(Type type){
    t = type;
  }
  public String toString(){
      return "TArray * " + t.toString();
  }
}
