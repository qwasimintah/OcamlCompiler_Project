import java.util.*;
import exp.*;
import ast.*;

public class ReductionNestedExpression implements ObjVisitor<Exp> {
  public Let insert(Let e){
    Exp exp = e.e1.accept(this);

    if (exp instanceof Let){
      Let exp_res_temp = (Let) exp;
      if (exp_res_temp.e2 instanceof Let) {
        Let e2_temp = (Let) exp_res_temp.e2;
        Let exp_res = new Let(exp_res_temp.id, exp_res_temp.t, exp_res_temp.e1, insert(e2_temp));
        return(exp_res);
      }
      else {
        Let exp_res = new Let(exp_res_temp.id, exp_res_temp.t, exp_res_temp.e1, new Let(e.id, e.t, exp_res_temp.e2, e.e2.accept(this)));
        return(exp_res);

      }
    }

    else {
      Let exp_res = new Let(e.id, e.t, exp, e.e2.accept(this));
      return(exp_res);
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
    return(insert(e));
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

  public Exp visit(App e) {
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
