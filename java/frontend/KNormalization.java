package frontend;

import java.util.*;
import exp.*;
import ast.*;
import ast.type.*;

/**
  * Implementation of the K-Normalization of an expression
  *
  *@author ApresShampoingManQuiDortPendantQueLesAutresTriment
  *
  */

public class KNormalization implements ObjVisitor<Exp> {


private Id id_generator = new Id("id");

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
  *@param e A variable
  *@return The variable given in entry
*/

public Exp visit(Var e) {
        return e;
}

/**
  *Visit the addition in the AST, and K-normalizes each of its child
  *
  *@param e An addition
  *@return The addition with the two children K-normalized
*/

public Exp visit(Add e) {
        Type t = new TInt();
        Var var1 = new Var(id_generator.gen());
        Var var2 = new Var(id_generator.gen());
        Add new_op = (new Add(var1, var2));
        Let new_let2 = (new Let(var2.id, t, e.e2.accept(this), new_op));
        Let new_let1 = (new Let(var1.id, t, e.e1.accept(this), new_let2));
        return new_let1;
}

/**
  *Visit the substraction in the AST, and K-normalizes each of its child
  *
  *@param e A substraction
  *@return The substraction with the two children K-normalized
*/


public Exp visit(Sub e) {
        Type t = new TInt();
        Var var1 = new Var(id_generator.gen());
        Var var2 = new Var(id_generator.gen());
        Sub new_op = (new Sub(var1, var2));
        Let new_let2 = (new Let(var2.id, t, e.e2.accept(this), new_op));
        Let new_let1 = (new Let(var1.id, t, e.e1.accept(this), new_let2));
        return new_let1;
}

/**
  *Visit the Let in the AST, and K-normalizes the expressions
  *
  *@param e A Let
  *@return A let K-normalized
*/


public Exp visit(Let e) {
        Let new_let = (new Let(e.id, e.t, e.e1.accept(this), e.e2.accept(this)));
        return new_let;
}

/**
  *Visit the LetRec in the AST, and K-normalizes the expressions
  *
  *@param e A LetRec
  *@return A LetRec K-normalized
*/

public Exp visit(LetRec e) {
        FunDef new_fun = new FunDef(e.fd.id, e.fd.type, e.fd.args, e.fd.e.accept(this));
        LetRec new_rec = (new LetRec(new_fun, e.e.accept(this)));
        return new_rec;
}

/**
  *Visit the App in the AST, and K-normalizes the expressions
  *
  *@param e A LetRec
  *@return A LetRec K-normalized
*/

public Exp visit(App e) {
  List<Let> list_let = new ArrayList<Let>();
  List<Exp> list_var = new ArrayList<Exp>();
  List<Exp> list_exp = new ArrayList<Exp>();
  Exp true_name = e.e;
  if (e.e instanceof App){
    App temp_app = (App) e.e;
    for (int k = 0; k < temp_app.es.size(); k++){
      list_var.add(new Var(id_generator.gen()));
      Var new_var = (Var) list_var.get(k);
      list_let.add(new Let(new_var.id, new TInt(), new Int(1), new Int(1)));
      list_exp.add(temp_app.es.get(k));
    }
    if (temp_app.e instanceof Var){
      true_name = (Var) temp_app.e;
    }
  }
  for (int j = 0; j < e.es.size(); j++){
    Var new_var = new Var(id_generator.gen());
    list_var.add(new_var);
    list_let.add(new Let(new_var.id, new TInt(), new Int(1), new Int(1)));
    list_exp.add(e.es.get(j));
  }
  for (int i = (list_exp.size() - 1); i >= 0; i--){
    Var new_var = (Var) list_var.get(i);
    Type t = new TInt();
    Exp es_temp = list_exp.get(i);
    if (i < list_exp.size() - 1){
      list_let.set(i, new Let(new_var.id, t, es_temp.accept(this), list_let.get(i+1)));
    }
    else {
        App new_app = new App(true_name, list_var);
        list_let.set(i, new Let(new_var.id, t, es_temp.accept(this), new_app));
    }
  }
  return list_let.get(0);
}

/**
  *Visit the Tuple in the AST, and K-normalizes the children
  *
  *@param e A Tuple
  *@return A Tuple K-normalized
*/


public Exp visit(Tuple e) {
  List<Exp> list_es = new ArrayList<Exp>();

  for (int i = 0; i < e.es.size(); i++){
    Exp es_temp = e.es.get(i);
    list_es.add(es_temp.accept(this));
  }
  return new Tuple(list_es);
}

/**
  *Visit the If in the AST, and K-normalizes the expressions
  *
  *@param e An If
  *@return A If K-normalized
*/


public Exp visit(If e) {
        if (e.e1 instanceof Eq) {
                Type t = new TInt();
                Eq ref_eq = (Eq) e.e1;
                Var var1 = new Var(id_generator.gen());
                Var var2 = new Var(id_generator.gen());
                Eq new_eq = new Eq(var1, var2);
                If new_if = new If(new_eq, e.e2.accept(this), e.e3.accept(this));
                Let let2 = new Let(var2.id, t, ref_eq.e2.accept(this), new_if);
                Let let1 = new Let(var1.id, t, ref_eq.e1.accept(this), let2);
                return let1;
        }
        else if (e.e1 instanceof LE) {
                Type t = new TInt();
                LE ref_le = (LE) e.e1;
                Var var1 = new Var(id_generator.gen());
                Var var2 =  new Var(id_generator.gen());
                LE new_le = new LE(var1, var2);
                If new_if = new If(new_le, e.e2.accept(this), e.e3.accept(this));
                Let let2 = new Let(var2.id, t, ref_le.e2.accept(this), new_if);
                Let let1 = new Let(var1.id, t, ref_le.e1.accept(this), let2);
                return let1;
        }
        else if (e.e1 instanceof Not) {
                Not ref_not = (Not) e.e1;
                if (ref_not.e instanceof Eq) {
                        Eq ref_eq = (Eq) ref_not.e;
                        If new_if = new If(new Eq(ref_eq.e1, ref_eq.e2), e.e3, e.e2);
                        return new_if.accept(this);
                }
                else if (ref_not.e instanceof LE) {
                        LE ref_le = (LE) ref_not.e;
                        If new_if = new If(new LE(ref_le.e1, ref_le.e2), e.e3, e.e2);
                        return new_if.accept(this);
                }
        }
        else if (e.e1 instanceof Var) {
                Var ref_var = (Var) e.e1;
                If new_if = new If(new Eq(ref_var, new exp.Bool(false)), e.e3, e.e2);
                return new_if.accept(this);
        }
        return e;
}

/**
  *Visit the Unit in the AST
  *
  *@param e An Unit
  *@return The Unit
*/


public Exp visit(Unit e) {
        return e;
}

/**
  *Visit the Array in the AST
  *
  *@param e An Array
  *@return The Array with its expression K-normalized
*/


public Exp visit(Array e) {
  Array new_array = new Array(e.e1.accept(this), e.e2);
  return new_array;
}

/**
  *Visit the Get in the AST
  *
  *@param e A Get
  *@return The Get
*/


public Exp visit(Get e) {
  return e;
}

/**
  *Visit the Put in the AST
  *
  *@param e A Put
  *@return A Put with the third attribute K-normalized
*/

public Exp visit(Put e) {
  Put new_put = new Put(e.e1, e.e2, e.e3.accept(this));
  return new_put;
}

/**
  *Visit the LEtTuple in the AST
  *
  *@param e A LetTuple
  *@return A LetTuple with the expressions K-normalized
*/

public Exp visit(LetTuple e) {
  return (new LetTuple(e.ids, e.ts, e.e1.accept(this), e.e2.accept(this)));
}

/**
  *Visit the Bool in the AST
  *
  *@param e A Bool
  *@return The Bool
*/

public Exp visit(exp.Bool e) {
        return e;
}

/**
  *Visit the Float in the AST
  *
  *@param e A Float
  *@return The Float
*/

public Exp visit(exp.Float e) {
        return e;
}

/**
  *Visit the Not in the AST
  *
  *@param e A Not
  *@return The Not
*/

public Exp visit(Not e) {
        return e;
}

/**
  *Visit the Neg in the AST
  *
  *@param e A Neg
  *@return The Neg
*/

public Exp visit(Neg e) {
        return e;
}

/**
  *Visit the FNeg in the AST
  *
  *@param e A FNeg
  *@return The FNeg
*/

public Exp visit(FNeg e) {
        return e;
}

/**
  *Visit the FAdd in the AST
  *
  *@param e A FAdd
  *@return The FAdd
*/

public Exp visit(FAdd e) {
        return e;
}

/**
  *Visit the FSub in the AST
  *
  *@param e A FSub
  *@return The FSub
*/

public Exp visit(FSub e) {
        return e;
}

/**
  *Visit the FMul in the AST
  *
  *@param e A FMul
  *@return The FMul
*/

public Exp visit(FMul e) {
        return e;
}

/**
  *Visit the FDiv in the AST
  *
  *@param e A FDiv
  *@return The FDiv
*/

public Exp visit(FDiv e) {
        return e;
}

/**
  *Visit the Eq in the AST
  *
  *@param e A Eq
  *@return The Eq
*/

public Exp visit(Eq e) {
        return e;
}

/**
  *Visit the LE in the AST
  *
  *@param e A LE
  *@return The LE
*/

public Exp visit(LE e) {
        return e;
}
}
