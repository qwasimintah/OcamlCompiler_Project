package frontend;

import exp.*;
import ast.*;
import java.util.*;

public class FreeVariables implements ObjVisitor<Exp>{
  private static HashSet<String> known = new HashSet<String> ();

  private HashMap<String, HashSet<String> > current_variables =  new HashMap<String, HashSet<String> > ();

  private static HashMap<String, HashSet<String> > free_variables = new HashMap<String, HashSet<String> > ();

  private Stack current_functions = new Stack();

  private static HashSet<String> set_of_functions = new HashSet<String> ();

  private HashSet<String> external_functions = new HashSet<String> ();
  {{
    external_functions.add("print_int");
    external_functions.add("print_newline");
    external_functions.add("truncate");
  }}

  public static HashSet<String> getKnown(){
    return known;
  }

  public static HashMap<String, HashSet<String> > getFree_variables(){
    return free_variables;
  }

  public static HashSet<String> getSet_of_functions(){
    return set_of_functions;
  }

  public Exp visit(Add e){
    Add new_add = new Add(e.e1.accept(this), e.e2.accept(this));
    return new_add;
  }

  public Exp visit(Sub e){
    Sub new_sub = new Sub(e.e1.accept(this), e.e2.accept(this));
    return new_sub;
  }

  public Exp visit(Let e){
    if (!current_functions.empty()){
      LetRec function = (LetRec) current_functions.peek();
      String current_function = function.fd.id.toString();
      HashSet set = current_variables.get(current_function);
      if (set == null){
        set = new HashSet();
        current_variables.put(current_function, set);
      }
      set.add(e.id.toString());
    }
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Let new_let = new Let(e.id, e.t, new_e1, new_e2);
    return new_let;
  }

  public Exp visit(Var e){
    if (!current_functions.empty()){
      LetRec function = (LetRec) current_functions.peek();
      String current_function = function.fd.id.toString();
      HashSet set = current_variables.get(current_function);
      boolean in_set = set.contains(e.id.toString());
      if (!set_of_functions.contains(e.id.toString()) && !external_functions.contains(e.id.toString())){
        for (int i = 0; i < current_functions.size(); i++){
          LetRec tmp_func = (LetRec) current_functions.get(i);
          HashSet set_free_variables = free_variables.get(tmp_func.fd.id.toString());
          if (set_free_variables == null){
            set_free_variables = new HashSet();
            free_variables.put(tmp_func.fd.id.toString(), set_free_variables);
          }
          HashSet tmp_set = current_variables.get(tmp_func.fd.id.toString());
          if (tmp_set == null){
            tmp_set = new HashSet();
            current_variables.put(tmp_func.fd.id.toString(), tmp_set);
          }
          if (!tmp_set.contains(e.id.toString()) && !in_set){
            set_free_variables.add(e.id.toString());
          }
        }
      }
    }
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
    set_of_functions.add(let_rec.fd.id.toString());
    current_functions.push(let_rec);
    known.add(let_rec.fd.id.toString());
    HashSet set = current_variables.get(let_rec.fd.id.toString());
    if (set == null){
      set = new HashSet();
      current_variables.put(let_rec.fd.id.toString(), set);
    }
    for (Id arg: let_rec.fd.args){
      set.add(arg.toString());
      for (int i = 0; i < current_functions.size(); i++){
        LetRec tmp_func = (LetRec) current_functions.get(i);
        String tmp_id = tmp_func.fd.id.toString();
        HashSet tmp_set = current_variables.get(tmp_id);
        tmp_set.add(arg.toString());
      }
    }
    System.out.println("let_rec: " + let_rec.fd.id.toString());
    System.out.println("free_variables av: " + free_variables.get(let_rec.fd.id.toString()));
    Exp new_exp_fd = let_rec.fd.e.accept(this);
    System.out.println("let_rec: " + let_rec.fd.id.toString());
    System.out.println("free_variables ap: " + free_variables.get(let_rec.fd.id.toString()));
    HashSet set_free_variables = free_variables.get(let_rec.fd.id.toString());
    if (set_free_variables == null){
      set_free_variables = new HashSet();
    }
    FunDef new_fd = new FunDef(let_rec.fd.id, let_rec.fd.type, let_rec.fd.args, new_exp_fd);
    current_functions.pop();
    Exp new_e = let_rec.e.accept(this);
    LetRec new_let_rec = new LetRec(new_fd, new_e);
    if (set_free_variables.isEmpty()){
      known.remove(let_rec.fd.id.toString());
    }
    System.out.println("free_variables: " + free_variables);
    System.out.println("known: " + known);
    System.out.println("set_of_functions: " + set_of_functions);
    return new_let_rec;
  }

  public Exp visit(App app){
    if (current_functions.size() > 1){
      Exp new_e = app.e.accept(this);
      List<Exp> new_es = new LinkedList<Exp> ();
      for (Exp tmp_exp: app.es){
        new_es.add(tmp_exp.accept(this));
      }
      App new_app = new App(new_e, new_es);
      return new_app;
    } else {
      return app;
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
    if (!current_functions.empty()){
      for (Id exp: e.ids){
        LetRec function = (LetRec) current_functions.peek();
        String current_function = function.fd.id.toString();
        HashSet set = current_variables.get(current_function);
        if (set == null){
          set = new HashSet();
          current_variables.put(current_function, set);
        }
        set.add(exp.toString());
      }
    }
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
