package frontend;

import java.util.*;
import exp.*;
import ast.*;

class EvaluationVisitor implements ObjVisitor<Integer> {
  public Integer visit(Int e) {
    return e.i;
  }

  public Integer visit(Add e) {
    return e.e1.accept(this) + e.e2.accept(this);
  }

  public Integer visit(Sub e) {
    return e.e2.accept(this) - e.e2.accept(this);
  }

  public Integer visit(Unit e) {
    return 1;
  }

<<<<<<< HEAD
public Integer visit(exp.Bool e) {
        return 1;
}

public Integer visit(exp.Float e) {
        return 1;
}
=======
  public Integer visit(Bool e) {
    return 1;
  }

  public Integer visit(Float e) {
    return 1;
  }
>>>>>>> c5f09029b2684ebf7a7daaa3754e8f69c54b68d0

  public Integer visit(Not e) {
    return 1;
  }

  public Integer visit(Neg e) {
    return 1;
  }

  public Integer visit(FNeg e) {
    return 1;
  }

  public Integer visit(FAdd e) {
    return 1;
  }

  public Integer visit(FSub e) {
    return 1;
  }

  public Integer visit(FMul e) {
    return 1;
  }

  public Integer visit(FDiv e) {
    return 1;
  }

  public Integer visit(Eq e) {
    return 1;
  }

  public Integer visit(LE e) {
    return 1;
  }

  public Integer visit(If e) {
    return 1;
  }

  public Integer visit(Let e) {
    return 1;
  }

  public Integer visit(Var e) {
    return 1;
  }

  public Integer visit(LetRec e) {
    return 1;
  }

  public Integer visit(App e) {
    return 1;
  }

  public Integer visit(Tuple e) {
    return 1;
  }

  public Integer visit(LetTuple e) {
    return 1;
  }

  public Integer visit(Array e) {
    return 1;
  }

  public Integer visit(Get e) {
    return 1;
  }

  public Integer visit(Put e) {
    return 1;
  }
}
