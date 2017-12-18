import java.util.*;

puclib class ReductionNestedExpression implements ObjVisitor<Exp> {
  public static Let insert(Let e){
    Exp exp = accept(e.e1);
    if (exp Instanceof Int){
      Exp exp_res = (Int) exp
      return(new Let(Id.gen(), new TInt, e.e1, accept(e.e2)));
    }

    If (exp Instanceof Let){
      Exp exp_res = (Let) exp
      exp_res.id = exp.id;
      exp_res.t = exp.t;
      exp_res.e1 =  exp.e1;
      exp_res.e2 = accept(exp.e2);
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

  public Exp visit(Bool e) {
    return e;
  }

  public Exp visit(Float e) {
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
