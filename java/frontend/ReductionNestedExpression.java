package frontend;

import java.util.*;
import exp.*;
import ast.*;

public class ReductionNestedExpression implements ObjVisitor<Exp> {
  public Exp insert(Let e, Exp exp){
    if (exp instanceof Let) {
      Let new_let = (Let) exp;
      return (new Let(new_let.id, new_let.t, new_let.e1, insert(e, new_let.e2)));
    }
    else if (exp instanceof LetRec) {
      LetRec new_let_rec =  (LetRec) exp;
      return (new LetRec(new_let_rec.fd, insert(e, new_let_rec.e)));
    }
    else if (exp instanceof LetTuple) {
      LetTuple new_let_tuple = (LetTuple) exp;
      return (new LetTuple(new_let_tuple.ids, new_let_tuple.ts, new_let_tuple.e1, insert(e, new_let_tuple.e2)));
    }
    else {
      return (new Let(e.id, e.t, exp, e.e2.accept(this)));
    }
  }

  public Exp insert_tuple(LetTuple e, Exp exp){
    if (exp instanceof Let) {
      Let new_let = (Let) exp;
      return (new Let(new_let.id, new_let.t, new_let.e1, insert_tuple(e, new_let.e2)));
    }
    else if (exp instanceof LetRec) {
      LetRec new_let_rec =  (LetRec) exp;
      return (new LetRec(new_let_rec.fd, insert_tuple(e, new_let_rec.e)));
    }
    else if (exp instanceof LetTuple) {
      LetTuple new_letTuple = (LetTuple) exp;
      return (new LetTuple(new_letTuple.ids, new_letTuple.ts, new_letTuple.e1, insert_tuple(e, new_letTuple.e2)));
    }
    else {
      return (new LetTuple(e.ids, e.ts, exp, e.e2.accept(this)));
    }
  }

public Exp visit(LetTuple e) {
    return insert_tuple(e, e.e1.accept(this));
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

public Exp visit(LetRec e) {
    FunDef new_fun = new FunDef(e.fd.id, e.fd.type, e.fd.args, e.fd.e.accept(this));
    LetRec new_let_rec = new LetRec(new_fun, e.e.accept(this)) ;
    return new_let_rec;
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
        If new_if = new If(e.e1.accept(this), e.e2.accept(this), e.e3.accept(this));
        return new_if;
}


public Exp visit(Tuple e) {
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
