package frontend;

import exp.*;
import ast.*;
import ast.type.*;
import java.util.*;

public class ClosureConversion implements ObjVisitor<Exp>{
  private HashSet<String> known = FreeVariables.getKnown();

  private boolean test_app = false;

  private HashMap<String, HashSet<String> > free_variables = FreeVariables.getFree_variables();

  private Stack current_functions = new Stack();

  public Exp visit(Add e){
    Add new_add = new Add(e.e1.accept(this), e.e2.accept(this));
    return new_add;
  }

  public Exp visit(Sub e){
    Sub new_sub = new Sub(e.e1.accept(this), e.e2.accept(this));
    return new_sub;
  }

  public Exp visit(Let e){
    //TODO
    e.e1.accept(this);
    e.e2.accept(this);
    return e;
  }

  public Exp visit(Var e){
    //TODO
    System.out.println("Var: " + e.id.toString());
    return e;
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

  public Exp visit(Not not){
    Not new_not = new Not(not.e.accept(this));
    return new_not;
  }

  public Exp visit(Neg neg){
    Neg new_neg = new Neg(neg.e.accept(this));
    return new_neg;
  }

  public Exp visit(FNeg neg){
    FNeg new_neg = new FNeg(neg.e.accept(this));
    return new_neg;
  }

  public Exp visit(FAdd e){
    FAdd new_fadd = new FAdd(e.e1.accept(this), e.e2.accept(this));
    return new_fadd;
  }

  public Exp visit(FSub e){
    FSub new_fsub = new FSub(e.e1.accept(this), e.e2.accept(this));
    return new_fsub;
  }

  public Exp visit(FMul e){
    FMul new_fmult = new FMul(e.e1.accept(this), e.e2.accept(this));
    return new_fmult;
  }

  public Exp visit(FDiv e){
    FDiv new_fdiv = new FDiv(e.e1.accept(this), e.e2.accept(this));
    return new_fdiv;
  }

  public Exp visit(Eq e){
    Eq new_eq = new Eq(e.e1.accept(this), e.e2.accept(this));
    return new_eq;
  }

  public Exp visit(LE e){
    LE new_le = new LE(e.e1.accept(this), e.e2.accept(this));
    return new_le;
  }

  public Exp visit(If e){
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Exp new_e3 = e.e3.accept(this);
    If new_if = new If(new_e1, new_e2, new_e3);
    return new_if;
  }

  public Exp visit(LetRec let_rec){
    //TODO
    current_functions.push(let_rec);
    if (current_functions.size() > 1 && known.contains(let_rec.fd.id.toString())){
      LetRec prev_func = (LetRec) current_functions.get(current_functions.size() - 2);
      List<Exp> list = new LinkedList<Exp> ();
      list.add(new Var(let_rec.fd.id));
      for (String id: free_variables.get(let_rec.fd.id.toString())){
        list.add(new Var(new Id(id)));
      }
      Exp new_prev_fd_e = new Tuple(list);
      List<Id> list_ids = new LinkedList<Id> ();
      List<Type> list_type = new LinkedList<Type> ();
      for (Exp exp: list){
        Id new_id = new Id("_");
        new_id = new_id.gen();
        list_ids.add(new_id);
        list_type.add(new TVar(new_id.toString()));
      }
      //Exp tuple = new Tuple()
      //LetTuple let_tuple = new LetTuple(list_ids, list_type, );
    }
    let_rec.fd.e.accept(this);
    let_rec.e.accept(this);
    return let_rec;
  }

  public Exp visit(App app){
    //TODO
    System.out.println("App");
    app.e.accept(this);
    for (Exp exp: app.es){
      exp.accept(this);
    }
    return app;
  }

  public Exp visit(Tuple e){
    List<Exp> new_list = new LinkedList<Exp>();
    for (Exp exp: e.es){
      Exp new_exp = exp.accept(this);
      new_list.add(new_exp);
    }
    Tuple new_tuple = new Tuple(new_list);
    return new_tuple;
  }

  public Exp visit(LetTuple e){
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    LetTuple new_e = new LetTuple(e.ids, e.ts, new_e1, new_e2);
    return new_e;
  }

  public Exp visit(Array e){
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Array new_e = new Array(new_e1, new_e2);
    return new_e;
  }

  public Exp visit(Get e){
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Get new_e = new Get(new_e1, new_e2);
    return new_e;
  }

  public Exp visit(Put e){
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Exp new_e3 = e.e3.accept(this);
    Put new_e = new Put(new_e1, new_e2, new_e3);
    return new_e;
  }
}
