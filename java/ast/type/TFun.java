package ast.type;

import java.util.*;

public class TFun extends Type {
    public String id;
    public Type dom = new TUnit();
    public List<Type> domlist = new ArrayList();
    public final Type codom;
    public final int nbArgs;

    public TFun(Type domain, Type codomain){
      this.dom = domain;
      this.nbArgs = 1;
      this.codom = codomain;
      this.toString();
    }

    public TFun(Type codomain){
      this.nbArgs = 0;
      this.codom = codomain;
    }

    public TFun(List<Type> domain, Type codomain){
      this.domlist = domain;
      this.codom = codomain;
      this.nbArgs = domain.size();
      this.toString();
    }

    public String toString(){
      if (this.nbArgs > 1){
        String str =  "(";
        int i;
        for (i = 0; i < this.domlist.size() - 1; i++){
          str += this.domlist.get(i) + ", ";
        }
        str += this.domlist.get(i) + " -> " + this.codom + ")";
        return str;
      } else {
      return  "(" + this.dom + " -> " + this.codom + ")";
      }
    }
}
