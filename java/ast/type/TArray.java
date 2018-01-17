package ast.type;

public class TArray extends Type {
  public Type t;

  public TArray(Type type){
    t = type; // Might not have been the easiest way for verification, but convenient for the rest.
  }
  public String toString(){
      return "TArray * " + t.toString();
  }
}
