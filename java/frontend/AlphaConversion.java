package frontend;

import java.util.*;
import exp.*;
import ast.*;

public class AlphaConversion implements ObjVisitor<Exp>{
  private HashMap<String, Stack> epsilon = new HashMap<String, Stack> ();

  private boolean sec_exp_let = false;

  private HashMap<String, Stack> used_in_let = new HashMap<String, Stack> ();

  private HashSet<String> functions = new HashSet();

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
    Stack stack = epsilon.get(e.id.toString());
    if (stack == null) {
      stack = new Stack();
      epsilon.put(e.id.toString(), stack);
    }
    stack.push(new_var.id.toString());
    Exp new_e1 = e.e1.accept(this);
    sec_exp_let = true;
    Exp new_e2 = e.e2.accept(this);
    Let new_let = new Let(new_var.id, e.t, new_e1, new_e2);
    HashSet set = new HashSet();
    Stack used_vars = used_in_let.get(e.id.toString());
    if (used_vars == null){
      used_vars = new Stack();
      used_in_let.put(e.id.toString(), used_vars);
    }
    while (!used_vars.empty()){
      String key = (String) used_vars.pop();
      if (!functions.contains(key)){
        Stack tmp_stack = epsilon.get(key);
        if (!tmp_stack.empty() && !set.contains(key)) {
          tmp_stack.pop();
          set.add(key);
        }
      }
    }
    return new_let;
  }

  public Exp visit(Var e){
    Stack stack = epsilon.get(e.id.toString());
    if (stack == null) {
      stack = new Stack();
      epsilon.put(e.id.toString(), stack);
    }
    if (!stack.empty()){
      if (!sec_exp_let){
        if (functions.contains(e.id.toString())){
          Var new_var = new Var(new Id((String) stack.peek()));
          return new_var;
        } else {
          Var new_var = new Var(e.id.gen());
          stack.push(new_var.id.toString());
          return new_var;
        }
      } else {
        Stack used_vars = used_in_let.get(e.id.toString());
        if (used_vars == null){
          used_vars = new Stack();
          used_in_let.put(e.id.toString(), used_vars);
        }
        used_vars.push(e.id.toString());
        Var new_var = new Var(new Id((String) stack.peek()));
        return new_var;
      }
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

  public Exp visit(App app){
    Exp new_exp = app.e.accept(this);
    List<Exp> new_list = new LinkedList<Exp>();
    for (Exp exp: app.es){
      Exp exp_list = exp.accept(this);
      new_list.add(exp_list);
    }
    App new_app = new App(new_exp, new_list);
    return new_app;
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
    List<Id> new_ids = new LinkedList<Id>();
    for (Id id: e.ids){
      Id new_id = id.gen();
      Stack stack = epsilon.get(id.toString());
      if (stack == null){
        stack = new Stack();
        epsilon.put(id.toString(), stack);
      }
      stack.push(new_id.toString());
      new_ids.add(new_id);
    }
    Exp new_e1 = e.e1.accept(this); 
    Exp new_e2 = e.e2.accept(this);
    LetTuple new_e = new LetTuple(new_ids, e.ts, new_e1, new_e2);
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

  public Exp visit(LetRec let_rec){
    functions.add(let_rec.fd.id.toString());
    Id new_id = let_rec.fd.id.gen();
    Stack stack = epsilon.get(let_rec.fd.id.toString());
    if (stack == null) {
      stack = new Stack();
    }
    stack.push(new_id.toString());
    epsilon.put(let_rec.fd.id.toString(), stack);
    List<Id> new_args = new LinkedList();
    for (Id arg: let_rec.fd.args){
      Id new_arg = arg.gen();
      Stack arg_stack = epsilon.get(arg.toString());
      if (arg_stack == null){
        arg_stack = new Stack();
        epsilon.put(arg.toString(), arg_stack);
      }
      arg_stack.push(new_arg.toString());
      new_args.add(new_arg);
    }
    sec_exp_let = true;
    Exp new_exp = let_rec.fd.e.accept(this);
    FunDef new_fd = new FunDef(new_id, let_rec.fd.type, new_args, new_exp);
    for (Id arg: let_rec.fd.args){
      Stack tmp_arg_stack = epsilon.get(arg.toString());
      if (!tmp_arg_stack.empty()) {
        tmp_arg_stack.pop();
      }
    }
    sec_exp_let = true;
    Exp let_rec_e = let_rec.e.accept(this);
    if (!stack.empty()){
      stack.pop();
    }
    sec_exp_let = false;
    LetRec new_let_rec = new LetRec(new_fd, let_rec_e);
    return new_let_rec;
  }
}
