package frontend;

import java.util.*;
import exp.*;
import ast.*;

public class ReductionNestedExpression implements ObjVisitor<Exp> {
  public Let insert(Let e, Exp exp){
    if (exp instanceof Let) {
      Let new_let = (Let) exp;
      return (new Let(new_let.id, new_let.t, new_let.e1, insert(e, new_let.e2)));
    }
    else {
      return (new Let(e.id, e.t, exp, e.e2.accept(this)));
    }
  }

public Exp visit(Int e) {
        return e;
}

public Exp visit (Var e) {
        return e;
}

public Exp visit(Add e) {
        return e;
}

public Exp visit(Sub e) {
        return e;
}

public Exp visit(Let e) {
  return(insert(e, e.e1.accept(this)));
}

public Exp visit(App e) {
  List<Exp> new_es = new ArrayList<Exp>();
  for (int i = 0; i < e.es.size(); i++){
    Exp es_temp = e.es.get(i);
    new_es.add(es_temp.accept(this));
  }
  return (new App(e.e.accept(this), new_es));
}

public Exp visit(Unit e) {
        return e;
}

public Exp visit(exp.Bool e) {
        return e;
}

public Exp visit(exp.Float e) {
        return e;
}

public Exp visit(Not e) {
        return e;
}

public Exp visit(Neg e) {
        return e;
}

public Exp visit(FNeg e) {
        return e;
}

public Exp visit(FAdd e) {
        return e;
}

public Exp visit(FSub e) {
        return e;
}

public Exp visit(FMul e) {
        return e;
}

public Exp visit(FDiv e) {
        return e;
}

public Exp visit(Eq e) {
        return e;
}

public Exp visit(LE e) {
        return e;
}

public Exp visit(If e) {
        return e;
}

public Exp visit(LetRec e) {
        return e;
}



public Exp visit(Tuple e) {
        return e;
}

public Exp visit(LetTuple e) {
        return e;
}

public Exp visit(Array e) {
        return e;
}

public Exp visit(Get e) {
        return e;
}

public Exp visit(Put e) {
        return e;
}
}
