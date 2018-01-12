package ast.type;

public class TFun extends Type {
    public final Type dom;
    public final Type codom;
    public TFun(Type domain, Type codomain){
      this.dom = domain;
      this.codom = codomain;
    }
}
