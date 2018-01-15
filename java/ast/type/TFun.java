package ast.type;

import java.util.*;

public class TFun extends Type {
    public final Type dom;
    // public final List<Type> dom;
    public final Type codom;

    public TFun(Type domain, Type codomain){
      // this.dom = new ArrayList<Type>();
      // this.dom.add(domain);
      this.dom =
      this.codom = codomain;
    }

    // public TFun(List<Type> domain, Type codomain){
    //   this.dom = domain;
    //   this.codom = codomain;
    // }

    public String toString(){
    //   String str =  "(";
    //   for (int i = 0; i < this.dom.size(); i++){
    //     str += this.dom.get(i);
    //   }
    //   str += " -> " + this.codom + ")";
    //   return str;
    return "(" + this.dom + " -> " + this.codom + ")";
    }
}
