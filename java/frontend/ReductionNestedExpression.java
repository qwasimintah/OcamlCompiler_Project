package frontend;

import java.util.*;
import exp.*;
import ast.*;

/**
  * Implementation of the reduction of Nested Let-Expressions
  *
  *@author balsamor
  *
  */


public class ReductionNestedExpression implements ObjVisitor<Exp> {

/**
  *Starting from a Let expression, it will insert the following Let or LetTuple in the good order
  *
  *@param e The Let that should be the final one
  *@param exp The expression we're matching now
  *@return The Let in the good order to make disappear the nested expression
*/
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

  /**
    *Starting from a LetTuple expression, it will insert the following Let or LetTuple in the good order
    *
    *@param e The LetTuple that should be the final one
    *@param exp The expression we're matching now
    *@return The LetTuple in the good order to make disappear the nested expression
  */

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
  /**
    *Visit the LetTuple and call the function that insert the LetTuple at the place
    *
    *@param e A LetTuple
    *@return The LetTuple in the good order to make disappear the nested expression
  */
public Exp visit(LetTuple e) {
    return insert_tuple(e, e.e1.accept(this));
}

/**
  *Visit the Int in the AST
  *
  *@param e An int
  *@return The int given in entry
*/

public Exp visit(Int e) {
        return e;
}

/**
  *Visit the Var in the AST
  *
  *@param e A Var
  *@return The Var given in entry
*/

public Exp visit (Var e) {
        return e;
}

/**
  *Visit the Add in the AST
  *
  *@param e An Add
  *@return The Add given in entry
*/

public Exp visit(Add e) {
        return e;
}

/**
  *Visit the Sub in the AST
  *
  *@param e A Sub
  *@return The Sub given in entry
*/

public Exp visit(Sub e) {
        return e;
}

/**
  *Visit the Let and call the function that insert the Let at the place
  *
  *@param e A Let
  *@return The Let in the good order to make disappear the nested expression
*/

public Exp visit(Let e) {
  return(insert(e, e.e1.accept(this)));
}

/**
  *Visit the App and reduces the nested expression if there are
  *
  *@param e An App
  *@return A new App after reduction of nested let-expressions
*/

public Exp visit(App e) {
  List<Exp> new_es = new ArrayList<Exp>();
  for (int i = 0; i < e.es.size(); i++){
    Exp es_temp = e.es.get(i);
    new_es.add(es_temp.accept(this));
  }
  return (new App(e.e.accept(this), new_es));
}

/**
  *Visit the LetRec and reduce nested expression in case there are Let or LetTuple in the children
  *
  *@param e A LetRec
  *@return The LetRec after reduction of nested let-expressions
*/

public Exp visit(LetRec e) {
    FunDef new_fun = new FunDef(e.fd.id, e.fd.type, e.fd.args, e.fd.e.accept(this));
    LetRec new_let_rec = new LetRec(new_fun, e.e.accept(this)) ;
    return new_let_rec;
}

/**
  *Visit the Unit in the AST
  *
  *@param e An Unit
  *@return The Unit given in entry
*/


public Exp visit(Unit e) {
        return e;
}

/**
  *Visit the Bool in the AST
  *
  *@param e A Bool
  *@return The Bool given in entry
*/


public Exp visit(exp.Bool e) {
        return e;
}

/**
  *Visit the Float in the AST
  *
  *@param e A Float
  *@return The Float given in entry
*/


public Exp visit(exp.Float e) {
        return e;
}

/**
  *Visit the Not in the AST
  *
  *@param e A Not
  *@return The Not given in entry
*/


public Exp visit(Not e) {
        return e;
}

/**
  *Visit the Neg in the AST
  *
  *@param e A Neg
  *@return The Neg given in entry
*/


public Exp visit(Neg e) {
        return e;
}

/**
  *Visit the FNeg in the AST
  *
  *@param e A FNeg
  *@return The FNeg given in entry
*/


public Exp visit(FNeg e) {
        return e;
}

/**
  *Visit the FAdd in the AST
  *
  *@param e A FAdd
  *@return The FAdd given in entry
*/


public Exp visit(FAdd e) {
        return e;
}

/**
  *Visit the FSub in the AST
  *
  *@param e A FSub
  *@return The FSub given in entry
*/


public Exp visit(FSub e) {
        return e;
}

/**
  *Visit the FMul in the AST
  *
  *@param e A FMul
  *@return The FMul given in entry
*/


public Exp visit(FMul e) {
        return e;
}

/**
  *Visit the FDiv in the AST
  *
  *@param e A FDiv
  *@return The FDiv given in entry
*/


public Exp visit(FDiv e) {
        return e;
}

/**
  *Visit the Eq in the AST
  *
  *@param e A Eq
  *@return The Eq given in entry
*/


public Exp visit(Eq e) {
        return e;
}

/**
  *Visit the LE in the AST
  *
  *@param e A LE
  *@return The LE given in entry
*/


public Exp visit(LE e) {
        return e;
}

/**
  *Visit the If in the AST
  *
  *@param e A If
  *@return The If with its child reduced of nested expressions
*/

public Exp visit(If e) {
        If new_if = new If(e.e1.accept(this), e.e2.accept(this), e.e3.accept(this));
        return new_if;
}

/**
  *Visit the Tuple in the AST
  *
  *@param e A Tuple
  *@return The Tuple given in entry
*/


public Exp visit(Tuple e) {
        return e;
}

/**
  *Visit the Array in the AST
  *
  *@param e A Array
  *@return The Array given in entry
*/

public Exp visit(Array e) {
        return e;
}

/**
  *Visit the Get in the AST
  *
  *@param e A Get
  *@return The Get given in entry
*/

public Exp visit(Get e) {
        return e;
}

/**
  *Visit the Put in the AST
  *
  *@param e A Put
  *@return The Put given in entry
*/

public Exp visit(Put e) {
        return e;
}
}
