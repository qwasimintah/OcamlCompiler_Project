<<<<<<< HEAD
package frontend;

import java.util.*;
import exp.*;
import ast.*;
=======
import java.util.HashSet;
>>>>>>> c5f09029b2684ebf7a7daaa3754e8f69c54b68d0

public class AlphaConversion implements ObjVisitor<Exp>{
  /**
  set of variables
  */
  private static HashSet<String> epsilon = new HashSet<String> ();

  public Exp visit(Add e){
    Add new_add = new Add(e.e1.accept(this), e.e2.accept(this));
    return new_add;
  }

  public Exp visit(Sub e){
    Sub new_sub = new Sub(e.e1.accept(this), e.e2.accept(this));
    return new_sub;
  }

  public Exp visit(Let e){
    Var new_var = new Var(e.id.gen());
    epsilon.add(new_var.id.toString());
    Let new_let = new Let(new_var.id, e.t, e.e1.accept(this), e.e2.accept(this));
    return new_let;
  }

  public Exp visit(Var e){
    if (!epsilon.add(e.id.toString())){
      Var new_var = new Var(e.id.gen());
      epsilon.add(new_var.id.toString());
      return new_var;
    } else {
      return e;
    }
  }

  public Exp visit(Int e){
    return e;
  }

  public Exp visit(Unit e){
    return e;
  }

  public Exp visit(exp.Bool e){
    return e;
  }

  public Exp visit(exp.Float e){
    return e;
  }

  public Exp visit(Not e){
    return e;
  }

  public Exp visit(Neg e){
    return e;
  }

  public Exp visit(FNeg e){
    return e;
  }

  public Exp visit(FAdd e){
    return e;
  }

  public Exp visit(FSub e){
    return e;
  }

  public Exp visit(FMul e){
    return e;
  }

  public Exp visit(FDiv e){
    return e;
  }

  public Exp visit(Eq e){
    return e;
  }

  public Exp visit(LE e){
    return e;
  }

  public Exp visit(If e){
    return e;
  }

  public Exp visit(LetRec e){
    return e;
  }

  public Exp visit(App e){
    return e;
  }

  public Exp visit(Tuple e){
    return e;
  }

  public Exp visit(LetTuple e){
    return e;
  }

  public Exp visit(Array e){
    return e;
  }

  public Exp visit(Get e){
    return e;
  }

  public Exp visit(Put e){
    return e;
  }
}
