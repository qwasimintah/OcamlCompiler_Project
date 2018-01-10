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
    String current_function = (String) current_functions.peek();
    HashSet set = current_variables.get(current_function);
    if (set == null){
      set = new HashSet();
    }
    set.add(e.id.toString());
    current_variables.put(current_function, set);
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Let new_let = new Let(e.id, e.t, new_e1, new_e2);
    return new_let;
  }

  public Exp visit(Var e){
    String current_function = (String) current_functions.peek();
    HashSet set = current_variables.get(current_function);
    boolean in_set = set.contains(e.id.toString());
    //System.out.println(current_function);
    //System.out.println(e.id.toString());
    //System.out.println(in_set);
    if (!in_set){
      HashSet set_free_variables = free_variables.get(e.id.toString());
      if (set_free_variables == null){
        set_free_variables = new HashSet();
      }
      set_free_variables.add(e.id.toString());
      free_variables.put(current_function, set_free_variables);
      //System.out.println(free_variables.get(e.id.toString()));
      //System.out.println(free_variables);
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

  public Exp visit(LetRec let_rec){
    if (test_app){
      in_known = known.contains(let_rec.fd.id.toString());
      return let_rec;
    } else {
      current_functions.push(let_rec.fd.id.toString());
      known.add(let_rec.fd.id.toString());
      HashSet set = current_variables.get(let_rec.fd.id.toString());
      if (set == null){
        set = new HashSet();
      }
      for (Id arg: let_rec.fd.args){
        set.add(arg.toString());
      }
      current_variables.put(let_rec.fd.id.toString(), set);
      Exp new_exp_fd = let_rec.fd.e.accept(this);
      System.out.println("let_rec: " + let_rec.fd.id.toString());
      //System.out.println(free_variables.get(let_rec.fd.id.toString()));
      HashSet set_free_variables = free_variables.get(let_rec.fd.id.toString());
      if (set_free_variables == null){
        set_free_variables = new HashSet();
      }
      if (!(set_free_variables.isEmpty())){
        //TODO
        System.out.println(free_variables.get(let_rec.fd.id.toString()));
        //System.out.println(known);
        Iterator<String> arg_iterator = (free_variables.get(let_rec.fd.id.toString())).iterator();
        LinkedList<Id> list_args = new LinkedList<Id> ();
        while (arg_iterator.hasNext()){
          String new_arg = arg_iterator.next();
          //System.out.println(new_arg);
          list_args.add(new Id(new_arg));
        }
        list_args.addAll(let_rec.fd.args);
        System.out.println(list_args);
        FunDef new_fd = new FunDef(let_rec.fd.id, let_rec.fd.type, list_args, let_rec.fd.e);
        LetRec new_let_rec = new LetRec(new_fd, let_rec.e);
        //new_let_rec.accept(new PrintVisitor());
        current_functions.pop();
        System.out.println("current_functions: " + current_functions);
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
        System.out.println("current_functions: " + current_functions);
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
      System.out.println("Known");
      return app;
    } else {
      //TODO
      System.out.println("Unknown");
      return app;
    }
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
