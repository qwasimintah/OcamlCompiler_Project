package frontend;

import exp.*;
import ast.*;
import java.util.*;

public class ClosureConversion implements ObjVisitor<Exp>{
  private HashSet<String> known = new HashSet<String> ();

  private boolean test_app = false;

  private boolean in_known = false;

  private HashMap<String, HashSet> current_variables =  new HashMap<String, HashSet> ();

  private HashMap<String, HashSet> free_variables = new HashMap<String, HashSet> ();

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
    if (!current_functions.empty()){
      //String current_function = (String) current_functions.peek();
      LetRec function = (LetRec) current_functions.peek();
      String current_function = function.fd.id.toString();
      HashSet set = current_variables.get(current_function);
      if (set == null){
        set = new HashSet();
      }
      set.add(e.id.toString());
      current_variables.put(current_function, set);
    }
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Let new_let = new Let(e.id, e.t, new_e1, new_e2);
    return new_let;
  }

  public Exp visit(Var e){
    //String current_function = (String) current_functions.peek();
    LetRec function = (LetRec) current_functions.peek();
    String current_function = function.fd.id.toString();
    HashSet set = current_variables.get(current_function);
    boolean in_set = set.contains(e.id.toString());
    //System.out.println("current_function: " + current_function);
    //System.out.println("var: " + e.id.toString());
    //System.out.println("in_set: " + in_set);
    //if (!in_set && !current_functions.contains(e.id.toString())){
    for (int i = 0; i < current_functions.size(); i++){
      LetRec func = (LetRec) current_functions.get(i);
      if (!in_set && !func.fd.id.toString().equals(e.id.toString())){
        HashSet set_free_variables = free_variables.get(e.id.toString());
        if (set_free_variables == null){
          set_free_variables = new HashSet();
        }
        set_free_variables.add(e.id.toString());
        free_variables.put(current_function, set_free_variables);
        //System.out.println(free_variables.get(e.id.toString()));
        //System.out.println(free_variables);
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
    if (test_app){
      in_known = known.contains(let_rec.fd.id.toString());
      return let_rec;
    } else {
      //current_functions.push(let_rec.fd.id.toString());
      current_functions.push(let_rec);
      known.add(let_rec.fd.id.toString());
      HashSet set = current_variables.get(let_rec.fd.id.toString());
      if (set == null){
        set = new HashSet();
      }
      set.add(let_rec.fd.id.toString());
      for (Id arg: let_rec.fd.args){
        set.add(arg.toString());
      }
      current_variables.put(let_rec.fd.id.toString(), set);
      System.out.println("let_rec: " + let_rec.fd.id.toString());
      System.out.println("free_variables av: " + free_variables.get(let_rec.fd.id.toString()));
      Exp new_exp_fd = let_rec.fd.e.accept(this);
      System.out.println("let_rec: " + let_rec.fd.id.toString());
      System.out.println("free_variables ap: " + free_variables.get(let_rec.fd.id.toString()));
      HashSet set_free_variables = free_variables.get(let_rec.fd.id.toString());
      if (set_free_variables == null){
        set_free_variables = new HashSet();
      }
      if (!(set_free_variables.isEmpty())){
        //TODO
        //System.out.println(free_variables.get(let_rec.fd.id.toString()));
        //System.out.println(known);
        Iterator<String> arg_iterator = (free_variables.get(let_rec.fd.id.toString())).iterator();
        LinkedList<Id> list_args = new LinkedList<Id> ();
        while (arg_iterator.hasNext()){
          String new_arg = arg_iterator.next();
          //System.out.println(new_arg);
          list_args.add(new Id(new_arg));
        }
        list_args.addAll(let_rec.fd.args);
        //System.out.println(list_args);
        FunDef new_fd = new FunDef(let_rec.fd.id, let_rec.fd.type, list_args, let_rec.fd.e);
        /*if (current_functions.size() > 1){
          //Experience
          LetRec prev_func = (LetRec) current_functions.get(current_functions.size() - 2);
          FunDef prev_fd = new FunDef()
          LetRec new_prev_func = new LetRec()
          LetRec new_let_rec = new LetRec(new_fd, prev_func);
        }*/
        Exp new_e = let_rec.e.accept(this);
        //LetRec new_let_rec = new LetRec(new_fd, let_rec.e);
        LetRec new_let_rec = new LetRec(new_fd, new_e);
        //new_let_rec.accept(new PrintVisitor());
        current_functions.pop();
        //System.out.println("current_functions: " + current_functions);
        return new_let_rec;
      } else {
        known.remove(let_rec.fd.id.toString());
        //System.out.println(known);
        //let_rec.e.accept(this;
        //TODO
        FunDef new_fd = new FunDef(let_rec.fd.id, let_rec.fd.type, let_rec.fd.args, new_exp_fd);
        LetRec new_let_rec = new LetRec(new_fd, let_rec.e);
        //new_let_rec.accept(new PrintVisitor());
        //return new_let_rec;
        current_functions.pop();
        //System.out.println("current_functions: " + current_functions);
        return new_let_rec;
      }
    }
  }

  public Exp visit(App app){
    test_app = true;
    app.e.accept(this);
    test_app = false;
    if (in_known){
      //TODO
      //System.out.println("Known");
      return app;
    } else {
      //TODO
      //System.out.println("Unknown");
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
    //TODO
    /*for (Id id: e.ids){
      HashSet set = current_variables.get(id.toString());
      if (set == null){
        set = new Stack();
        current_variables.put(id.toString(), set);
      }
      set.add(id.toString());
    }*/
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    LetTuple new_e = new LetTuple(e.ids, e.ts, new_e1, new_e2);
    return e;
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
