package frontend;

import exp.*;
import ast.*;
import ast.type.*;
import java.util.*;

public class ClosureConversion implements ObjVisitor<Exp>{
  private List<Closure> func_list= new LinkedList<Closure> ();

  private HashMap<String, HashSet<String> > free_variables = FreeVariables.getFree_variables();

  private HashSet<String> set_of_functions = FreeVariables.getSet_of_functions();

  private Stack current_let_rec = new Stack();

  private String current_app = null;

  private boolean in_app = false;

  private boolean moshi_moshi = false;

  private HashSet<String> var_call = new HashSet<String> ();

  private HashMap<String, Boolean> closure_done = new HashMap<String, Boolean> ();

  private Let kyrie_irving = null;

  public Exp visit(Add e){
    Add new_add = new Add(e.e1.accept(this), e.e2.accept(this));
    return new_add;
  }

  public Exp visit(Sub e){
    Sub new_sub = new Sub(e.e1.accept(this), e.e2.accept(this));
    return new_sub;
  }

  public Exp visit(Let e){
    Exp new_e1 = e.e1.accept(this);
    if (moshi_moshi){
      var_call.add(e.id.toString());
    }
    Exp new_e2 = e.e2.accept(this);
    Let new_e = new Let(e.id, e.t, new_e1, new_e2);
    return new_e;
  }

  public Exp visit(Var e){
    if (in_app){
      Var new_var = null;
      if (set_of_functions.contains(e.id.toString())){
        //current_app.push("_" + e.id.toString());
        current_app = "_" + e.id.toString();
        HashSet<String> set_free_variables = free_variables.get(e.id.toString());
        if (set_free_variables.isEmpty()){
          new_var = new Var(new Id("apply_direct"));
        } else {
          if (!closure_done.get(e.id.toString())){
            Id id = new Id("");
            id = id.gen();
            LetRec function = (LetRec) current_let_rec.peek();
            new_var = new Var(new Id("make_closure"));
            List<Exp> list = new LinkedList<Exp> ();
            list.add(new Var(new Id("_" + e.id.toString())));
            LetRec prev_fun = (LetRec) current_let_rec.peek();
            for (Id arg: prev_fun.fd.args){
              list.add(new Var(arg))  ;
            }
            kyrie_irving = new Let(id, function.fd.type, new App(new_var, list), new Var(id));
            closure_done.put(e.id.toString(), true);
            return kyrie_irving;
          } else {
            new_var = new Var(new Id("apply_closure"));
          }
        }
      } else {
        if (!moshi_moshi && var_call.contains(e.id.toString())){
          current_app = e.id.toString();
          new_var = new Var(new Id("apply_closure"));
        } else {
          new_var = e;
        }
      }
      return new_var;
    } else {
      if (set_of_functions.contains(e.id.toString()) && current_let_rec.size() > 1){
        Id id = new Id("");
        id = id.gen();
        LetRec function = (LetRec) current_let_rec.peek();
        Var new_var = new Var(new Id("make_closure"));
        List<Exp> list = new LinkedList<Exp> ();
        list.add(new Var(new Id("_" + e.id.toString())));
        LetRec prev_fun = (LetRec) current_let_rec.get(current_let_rec.size() - 2);
        for (Id arg: prev_fun.fd.args){
          list.add(new Var(arg))  ;
        }
        Let new_let = new Let(id, function.fd.type, new App(new_var, list), new Var(id));
        closure_done.put(e.id.toString(), true);
        return new_let;
      }
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
    current_let_rec.push(let_rec);
    closure_done.put(let_rec.fd.id.toString(), false);
    HashSet<String> set_free_variables = free_variables.get(let_rec.fd.id.toString());
    String label = "_" + let_rec.fd.id.toString();
    List<String> args = new LinkedList<String> ();
    for (Id id: let_rec.fd.args){
      args.add(id.toString());
    }
    Exp new_e = null;
    Closure closure = null;
    Exp new_fd_e = let_rec.fd.e.accept(this);
    if (set_free_variables.isEmpty()){
      closure = new Closure(label, null, args, new_fd_e);
      current_let_rec.pop();
      new_e = let_rec.e.accept(this);
    } else {
      List<String> free_list = new LinkedList<String> ();
      free_list.addAll(set_free_variables);
      closure = new Closure(label, free_list, args, new_fd_e);
      new_e = let_rec.e.accept(this);
      current_let_rec.pop();
    }
    func_list.add(closure);
    System.out.println("Closure list: ");
    System.out.println("closure numbers: " + func_list.size());
    for (Closure clos: func_list){
      System.out.println("\tlabel: " + clos.getLabel());
      System.out.println("\tfree_list: " + clos.getFree_list());
      System.out.println("\targs: " + clos.getArgs());
      System.out.println("\tcode: ");
      clos.getCode().accept(new PrintVisitor());
      System.out.println("");
    }
    return new_e;
  }

  public Exp visit(App app){
    in_app = true;
    moshi_moshi = false;
    Exp new_e = app.e.accept(this);
    moshi_moshi = true;
    List<Exp> new_list = new LinkedList<Exp> ();
    for (Exp exp: app.es){
      new_list.add(exp.accept(this));
    }
    if (kyrie_irving != null){
      new_list.add(0, new Var(kyrie_irving.id));
      App new_app = new App(new Var(new Id("apply_closure")), new_list);
      Let new_let = new Let(kyrie_irving.id, kyrie_irving.t, kyrie_irving.e1, new_app);
      kyrie_irving = null;
      return new_let;
    } else {
      if (current_app != null){
        new_list.add(0, new Var(new Id(current_app)));
        current_app = null;
      }
      App new_app = new App(new_e, new_list);
      in_app = false;
      return new_app;
    }
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
